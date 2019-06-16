/*
 * Extractor.java
 *
 * Copyright (C) 2018 Emmanuel Messulam<emmanuelbendavid@gmail.com>,
 * Raymond Lai <airwave209gt@gmail.com> and Contributors.
 *
 * This file is part of Amaze File Manager.
 *
 * Amaze File Manager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.amaze.filemanager.filesystem.compressed.extractcontents;

import android.content.Context;
import android.support.annotation.NonNull;

import com.amaze.filemanager.filesystem.FileUtil;
import com.amaze.filemanager.utils.ServiceWatcherUtil;
import com.amaze.filemanager.utils.files.GenericCopyUtil;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static com.amaze.filemanager.filesystem.compressed.CompressedHelper.SEPARATOR;
import static com.amaze.filemanager.filesystem.compressed.CompressedHelper.SEPARATOR_CHAR;

public abstract class Extractor {

    protected Context context;
    protected String filePath, outputPath;
    protected OnUpdate listener;
    protected List<String> invalidArchiveEntries;

    public Extractor(Context context, String filePath, String outputPath,
                     Extractor.OnUpdate listener) {
        this.context = context;
        this.filePath = filePath;
        this.outputPath = outputPath;
        this.listener = listener;
        this.invalidArchiveEntries = new ArrayList<>();
    }

    public void extractFiles(String[] files) throws IOException {
        HashSet<String> filesToExtract = new HashSet<>(files.length);
        Collections.addAll(filesToExtract, files);

        extractWithFilter((relativePath, isDir) -> {
            if(filesToExtract.contains(relativePath)) {
                if(!isDir) filesToExtract.remove(relativePath);
                return true;
            } else {// header to be extracted is at least the entry path (may be more, when it is a directory)
                for (String path : filesToExtract) {
                    if(relativePath.startsWith(path) || relativePath.startsWith("/"+path)) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    protected void extractEntryTar(@NonNull final Context context, TarArchiveInputStream inputStream,
                                   TarArchiveEntry entry, String outputDir) throws IOException {
        File outputFile = new File(outputDir, fixEntryName(entry.getName()));

        if (!outputFile.getCanonicalPath().startsWith(outputDir)){
            throw new IOException("Incorrect TarArchiveEntry path!");
        }

        if (entry.isDirectory()) {
            FileUtil.mkdir(outputFile, context);
            return;
        }

        if (!outputFile.getParentFile().exists()) {
            FileUtil.mkdir(outputFile.getParentFile(), context);
        }

        BufferedOutputStream outputStream = new BufferedOutputStream(
                FileUtil.getOutputStream(outputFile, context));
        try {
            int len;
            byte buf[] = new byte[GenericCopyUtil.DEFAULT_BUFFER_SIZE];
            while ((len = inputStream.read(buf)) != -1) {
                if (!listener.isCancelled()) {
                    outputStream.write(buf, 0, len);
                    ServiceWatcherUtil.position += len;
                } else break;
            }
        } finally {
            outputStream.close();
        }
    }

    public void extractEverything() throws IOException {
        extractWithFilter((relativePath, isDir) -> true);
    }

    public List<String> getInvalidArchiveEntries(){
        return invalidArchiveEntries;
    }

    protected abstract void extractWithFilter(@NonNull Filter filter) throws IOException;

    protected interface Filter {
        boolean shouldExtract(String relativePath, boolean isDirectory);
    }

    public interface OnUpdate {
        void onStart(long totalBytes, String firstEntryName);
        void onUpdate(String entryPath);
        void onFinish();
        boolean isCancelled();
    }

    protected String fixEntryName(String entryName){
        if(entryName.indexOf('\\') >= 0) {
            return fixEntryName(entryName.replaceAll("\\\\", SEPARATOR));
        } else if(entryName.indexOf(SEPARATOR_CHAR) == 0) {
            //if entryName starts with "/" (e.g. "/test.txt"), strip the prefixing "/"s
            return entryName.replaceAll("^/+", "");
        } else {
            return entryName;
        }
    }
}
