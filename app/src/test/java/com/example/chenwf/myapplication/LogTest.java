/* Copyright (C) 2014 The Android Open Source Project
        * Copyright (c) 1994, 2013, Oracle and/or its affiliates. All rights reserved.
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

package com.example.chenwf.myapplication;

import org.junit.Test;

/**
 * @author chenwf
 * @date 2018/10/9
 * @company Fotile智能厨电研究院
 */
public class LogTest {

    @Test
    public void TestWriteToFile() {
        LogUtils.getInstance().setLogType(LogUtils.LogType.FILE).setOutPutFolderPath("d:/logs");
        LogUtils.i("我就是我哈哈哈");
        LogUtils.w("你是风儿");
        LogUtils.d("我是沙啊");
        LogUtils.e("我是error啊");
    }

    @Test
    public void TestWriteErrorToFile() {
        LogUtils.getInstance().setLogType(LogUtils.LogType.FILE).setOutPutFolderPath("d:/logs");
        LogUtils.e("我是error啊" + System.currentTimeMillis());
        LogUtils.e("我是error啊" + System.currentTimeMillis());
        LogUtils.e("我是error啊" + System.currentTimeMillis());
    }

    @Test
    public void TestWriteFatalToFile() {
        LogUtils.getInstance().setLogType(LogUtils.LogType.FILE).setOutPutFolderPath("d:/logs");
        LogUtils.f("我是Fatal啊" + System.currentTimeMillis());
        LogUtils.f("我是Fatal啊" + System.currentTimeMillis());
        LogUtils.f("我是Fatal啊" + System.currentTimeMillis());
    }

    @Test
    public void TestWriteToConsole() {
        LogUtils.getInstance().setLogType(LogUtils.LogType.CONSOLE);
        LogUtils.i("你是风啊");
        LogUtils.w("你是风儿");
        LogUtils.d("我是沙啊");
        LogUtils.e("缠缠绵绵");
        LogUtils.f("到天涯");
    }

    @Test
    public void TestHadTAG() {
        LogUtils.getInstance().setLogType(LogUtils.LogType.CONSOLE);
        String tag = "我是TAG";
        LogUtils.i(tag, "你是风啊");
        LogUtils.w(tag, "你是风儿");
        LogUtils.d(tag, "我是沙啊");
        LogUtils.e(tag, "缠缠绵绵");
        LogUtils.f(tag, "到天涯");
    }

}
