package com.example.plugin.statistic.mt

import com.example.plugin.statistic.StatisticPlugin
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Type
import org.objectweb.asm.commons.AdviceAdapter

class MethodTimerAdviceAdapter extends AdviceAdapter {

    String methodOwner
    String methodName
    String desc
    int slotIndex

    MethodTimerAdviceAdapter(int api, MethodVisitor methodVisitor, String owner, int access, String name, String desc) {
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
//                slotIndex = newLocal(Type.LONG_TYPE)
//                mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false)

                mv.visitLdcInsn("")
//                mv.visitVarInsn(ALOAD, 0)//this
                mv.visitLdcInsn(methodOwner)//className
                mv.visitLdcInsn(methodName)//methodbName
                mv.visitLdcInsn(getArgsType(Type.getArgumentTypes(desc)))//argsTypes
                mv.visitLdcInsn(returnType.className)//returntype
                mv.visitInsn(ICONST_0)
                mv.visitTypeInsn(ANEWARRAY, "java/lang/Object")
                mv.visitMethodInsn(INVOKESTATIC, "com/miqt/pluginlib/tools/MethodHookHandler", "enter",
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
//                mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false)
//                mv.visitVarInsn(LLOAD, slotIndex)
//                mv.visitInsn(LSUB)
//                mv.visitVarInsn(LSTORE, slotIndex)
//                mv.visitVarInsn(LLOAD, slotIndex)
//                mv.visitLdcInsn(new Long(entity.getTime()))
//                mv.visitInsn(LCMP)
//                Label label0 = new Label()
//                mv.visitJumpInsn(IFLE, label0)
//                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
//                mv.visitTypeInsn(NEW, "java/lang/StringBuilder")
//                mv.visitInsn(DUP)
//                mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false)
//                mv.visitLdcInsn(methodOwner + "/" + methodName + " --> execution time : (")
//                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
//                mv.visitVarInsn(LLOAD, slotIndex)
//                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false)
//                mv.visitLdcInsn("ms)")
//                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
//                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false)
//                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false)
//                mv.visitLabel(label0)

                mv.visitLdcInsn("")
//                mv.visitVarInsn(ALOAD, 0)//this
                mv.visitLdcInsn("")
                mv.visitLdcInsn(methodOwner)//className
                mv.visitLdcInsn(methodName)//methodbName
                mv.visitLdcInsn(getArgsType(Type.getArgumentTypes(desc)))//argsTypes
                mv.visitLdcInsn(returnType.className)//returntype
                mv.visitInsn(ICONST_0)
                mv.visitTypeInsn(ANEWARRAY, "java/lang/Object")
                mv.visitMethodInsn(INVOKESTATIC, "com/miqt/pluginlib/tools/MethodHookHandler", "exit",
                        "(" +
                                "Ljava/lang/Object;" + //return
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
