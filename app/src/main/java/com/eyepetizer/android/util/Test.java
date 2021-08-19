package com.eyepetizer.android.util;

public class Test {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String str = "--- I'm the code line ---";
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        if(time > 500){
            System.out.println("程序运行时间： " + time + "ms");
        }
    }

}
/*


// class version 52.0 (52)
// access flags 0x21
public class com/eyepetizer/android/util/Test {

// compiled from: Test.java

// access flags 0x1
public <init>()V
        L0
        LINENUMBER 3 L0
        ALOAD 0
        INVOKESPECIAL java/lang/Object.<init> ()V
        RETURN
        L1
        LOCALVARIABLE this Lcom/eyepetizer/android/util/Test; L0 L1 0
        MAXSTACK = 1
        MAXLOCALS = 1

// access flags 0x9
public static main([Ljava/lang/String;)V
        // parameter  args
        L0
        LINENUMBER 6 L0
        INVOKESTATIC java/lang/System.currentTimeMillis ()J
        LSTORE 1
        L1
        LINENUMBER 7 L1
        LDC "--- I'm the code line ---"
        ASTORE 3
        L2
        LINENUMBER 8 L2
        INVOKESTATIC java/lang/System.currentTimeMillis ()J
        LSTORE 4
        L3
        LINENUMBER 9 L3
        LLOAD 4
        LLOAD 1
        LSUB
        LSTORE 6
        L4
        LINENUMBER 10 L4
        LLOAD 6
        LDC 500
        LCMP
        IFLE L5
        L6
        LINENUMBER 11 L6
        GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
        NEW java/lang/StringBuilder
        DUP
        INVOKESPECIAL java/lang/StringBuilder.<init> ()V
        LDC "\u7a0b\u5e8f\u8fd0\u884c\u65f6\u95f4\uff1a "
        INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
        LLOAD 6
        INVOKEVIRTUAL java/lang/StringBuilder.append (J)Ljava/lang/StringBuilder;
        LDC "ms"
        INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
        INVOKEVIRTUAL java/lang/StringBuilder.toString ()Ljava/lang/String;
        INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
        L5
        LINENUMBER 13 L5
        FRAME FULL [[Ljava/lang/String; J java/lang/String J J] []
        RETURN
        L7
        LOCALVARIABLE args [Ljava/lang/String; L0 L7 0
        LOCALVARIABLE startTime J L1 L7 1
        LOCALVARIABLE str Ljava/lang/String; L2 L7 3
        LOCALVARIABLE endTime J L3 L7 4
        LOCALVARIABLE time J L4 L7 6
        MAXSTACK = 4
        MAXLOCALS = 8
        }
*/
