/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

import java.io.*;

public class Reverse {

    public static void main (String[] args) throws Exception {
	FileReader r1 = new FileReader(args[0]);
	BufferedReader reader = new BufferedReader(r1);
	BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));

	String line;

	while ((line = reader.readLine()) != null) {
	    int len = line.length();
	    char[] buf = new char [len];
	    for (int i=0, j=len-1; i<len; i++, j--) {
		buf[i] = line.charAt(j);
	    }
	    String s = new String(buf);
	    writer.write(s);
	    writer.newLine();
	}
	reader.close();
	writer.close();
    }
}
