#
# Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
# DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
#
# This code is free software; you can redistribute it and/or modify it
# under the terms of the GNU General Public License version 2 only, as
# published by the Free Software Foundation.  Oracle designates this
# particular file as subject to the "Classpath" exception as provided
# by Oracle in the LICENSE file that accompanied this code.
#
# This code is distributed in the hope that it will be useful, but WITHOUT
# ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
# FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
# version 2 for more details (a copy is included in the LICENSE file that
# accompanied this code).
#
# You should have received a copy of the GNU General Public License version
# 2 along with this work; if not, write to the Free Software Foundation,
# Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
#
# Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
# or visit www.oracle.com if you need additional information or have any
# questions.
#

# Configure macros for calling the bdb configure script.

AC_DEFUN_ONCE([BDB_CALL_CONFIGURE],
[
  if test "x$USE_EXTERNAL_LIBDB" = "xfalse"; then
    BDB_LIBRARY_NAME="${LIBRARY_PREFIX}db-rds${SHARED_LIBRARY_SUFFIX}"
    BDB_CFLAGS="-I${BDB_DIST}"

    if test "x$OPENJDK_TARGET_OS" != "xwindows"; then
      # Don't set LD on linux since we point it to CC and bdb expects ld.
      BDB_CONFIGURE_OPTS="--disable-static CC=$CC"

      # 64-bit Solaris compile needs to specify appropriate addressing model
      if test "x${OPENJDK_TARGET_OS}-${OPENJDK_TARGET_CPU_BITS}" = "xsolaris-64"; then
        BDB_CONFIGURE_OPTS="$BDB_CONFIGURE_OPTS CFLAGS=-m64"
      fi
      # TODO: Add cross compile args if needed

      # Call BDB configure script
      $MKDIR -p ${OUTPUT_ROOT}/bdb/out
      AC_MSG_NOTICE([>>> Running configure for bdb: <<<])
      AC_MSG_NOTICE([    dir: ${OUTPUT_ROOT}/bdb/out])
      AC_MSG_NOTICE([    cmd: ${BDB_CONFIGURE_VARS} $SH ${BDB_TOPDIR}/dist/configure ${BDB_CONFIGURE_OPTS}])
      (cd ${OUTPUT_ROOT}/bdb/out && $SH ${BDB_TOPDIR}/dist/configure ${BDB_CONFIGURE_OPTS})
      if test ! -f "${OUTPUT_ROOT}/bdb/out/Makefile"; then
        AC_MSG_ERROR([Configure for bdb failed])
      fi
      AC_MSG_NOTICE([>>> Done running configure for bdb <<<])
    fi
  fi
  BDB_CFLAGS="${BDB_CFLAGS} -DBDB_LIB_NAME='\"${BDB_LIBRARY_NAME}\"'"
  AC_SUBST([BDB_CFLAGS])
  AC_SUBST([BDB_LIBRARY_NAME])
])
