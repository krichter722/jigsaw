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
import java.util.*;

public class Compare {


    static void print(String s) throws IOException {
	System.out.println (s);
    }

    static String lastComponent(String path) {
        int last = path.lastIndexOf('/');
        if (last == -1) {
	    throw new RuntimeException ("unexecptefd string");
        }
    	return path.substring(last+1);
    }

    public static void main (String[] args) throws Exception {
	FileReader r1 = new FileReader(args[0]);
	BufferedReader reader = new BufferedReader(r1);
	LinkedList<String> lines = new LinkedList<String>();

	String line;

	while ((line = reader.readLine()) != null) {
	    lines.add(line);
	}
	reader.close();
	int numlines = lines.size();
	ListIterator<String> iter = lines.listIterator(0);
	String prev = null, curr = null, next = null;
	int numok = 0;
    	String currName = null;
    	String nextName = null;
    	String prevName = null;
	while (iter.hasNext()) {
	    if (curr == null) {
	    	curr = iter.next();
	    	currName = lastComponent(curr);
	    }
	    if (iter.hasNext()) {
	    	next = iter.next();
	    	nextName = lastComponent(next);
	    } else {
		next = null;
		nextName = null;
	    }
	    if (currName.equals(prevName) || currName.equals(nextName)) {
		numok ++;
	    } else {
		print (curr);
	    }
	    prev = curr; prevName = currName;
	    curr = next; currName = nextName;
	}
	print("Number ok = " + numok);
    }
}
