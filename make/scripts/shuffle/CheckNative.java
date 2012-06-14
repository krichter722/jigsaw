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

/**
 * java CheckNative inputFile
 *
 * where inputFile is a classlist (output by jigsaw build)
 * Prints to stdout names of any classes with native methods.
 */
import java.io.*;
import java.lang.reflect.*;

public class CheckNative {
    public static void main (String[] args) throws Exception {
	boolean verbose = args.length > 1 && args[1].equals("-v");

	BufferedReader reader = new BufferedReader(
	    new FileReader(args[0])
	);
	String line;
	while ((line=reader.readLine()) != null) {
	    String name = line.replace('/', '.');
	    name = name.substring(0, name.lastIndexOf(".class"));
	    try {
	        Class clazz = Class.forName (name, false, CheckNative.class.getClassLoader());
	        Method[] methods = clazz.getDeclaredMethods();
	        for (int i=0; i<methods.length; i++) {
		    int modifier = methods[i].getModifiers();
		    if ((modifier & Modifier.NATIVE) != 0) {
			if (verbose) {
			    System.out.println (methods[i]);
			} else {
		            System.out.println (line);
			}
		        break;
		    }
	        }
	    } catch (UnsatisfiedLinkError e) {
		System.err.println ("ULE: " + line);
	    } catch (Exception e) {
		System.err.println (e.toString());
	    }
	}
    }
}
