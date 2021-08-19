package com.example.plugin.statistic.mt

import com.example.plugin.statistic.StatisticPlugin
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Type
import org.objectweb.asm.commons.AdviceAdapter

/**
 * 调用外部方法,自己实现打印
 */
class MethodTimerAdapter extends AdviceAdapter {

    String methodOwner
    String methodName
    String desc

    MethodTimerAdapter(int api, MethodVisitor methodVisitor, String owner, int access, String name, String desc) {
        super(api, methodVisitor, access, name, desc)
        this.methodOwner = owner
        this.methodName = name
        this.desc = desc
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter()
        for (MethodTimerEntity entity : StatisticPlugin.METHOD_TIMER_LIST) {
            if (methodOwner.contains(entity.getOwner())) {

                mv.visitLdcInsn("")
//                mv.visitVarInsn(ALOAD, 0)//this
                mv.visitLdcInsn(methodOwner)//className
                mv.visitLdcInsn(methodName)//methodbName
                mv.visitLdcInsn(getArgsType(Type.getArgumentTypes(desc)))//argsTypes
                mv.visitLdcInsn(returnType.className)//returntype
                mv.visitInsn(ICONST_0)
                mv.visitTypeInsn(ANEWARRAY, "java/lang/Object")
                mv.visitMethodInsn(INVOKESTATIC, "com/eyepetizer/android/util/MethodHook", "enter",
                        "(" +
                                "Ljava/lang/Object;" +
                                "Ljava/lang/String;" +
                                "Ljava/lang/String;" +
                                "Ljava/lang/String;" +
                                "Ljava/lang/String;" +
                                "[Ljava/lang/Object;" +
                                ")V",
                        false)
            }
        }
    }

    @Override
    void onMethodExit(int opcode) {
        for (MethodTimerEntity entity : StatisticPlugin.METHOD_TIMER_LIST) {
            if (methodOwner.contains(entity.getOwner())) {

//                mv.visitVarInsn(ALOAD, 0)//this
                mv.visitLdcInsn("")
                mv.visitLdcInsn(methodOwner)//className
                mv.visitLdcInsn(methodName)//methodbName
                mv.visitLdcInsn(getArgsType(Type.getArgumentTypes(desc)))//argsTypes
                mv.visitLdcInsn(returnType.className)//returntype
                mv.visitInsn(ICONST_0)
                mv.visitTypeInsn(ANEWARRAY, "java/lang/Object")
                mv.visitMethodInsn(INVOKESTATIC, "com/eyepetizer/android/util/MethodHook", "exit",
                        "(" +
                                "Ljava/lang/Object;" + //this
                                "Ljava/lang/String;" +
                                "Ljava/lang/String;" +
                                "Ljava/lang/String;" +
                                "Ljava/lang/String;" +
                                "[Ljava/lang/Object;" +//prams
                                ")V",
                        false)
            }
        }
        super.onMethodExit(opcode)
    }

    static String getArgsType(argsTypes) {
        if (argsTypes == null)
            return "null"

        int iMax = argsTypes.length - 1
        if (iMax == -1)
            return "[]"

        StringBuilder b = new StringBuilder()
        b.append('[')
        for (int i = 0; ; i++) {
            b.append(String.valueOf(argsTypes[i].className))
            if (i == iMax)
                return b.append(']').toString()
            b.append(", ")
        }
    }
}
