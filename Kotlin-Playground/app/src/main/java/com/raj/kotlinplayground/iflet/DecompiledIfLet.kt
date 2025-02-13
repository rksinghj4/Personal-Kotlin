package com.raj.kotlinplayground.iflet

class DecompiledIfLet {

    private var user: User? = User("Raj")

    /**
     * public final void testIf() {
     *       if (this.user != null) {
     *          User var1 = this.user;
     *          System.out.println(var1);
     *       }
     *
     *    }
     */
    fun testIf() {
        if (user != null) {
            println(user)
        }
    }

    /**
     *  public final void testLet() {
     *       User var10000 = this.user;
     *       if (var10000 != null) {
     *          User var1 = var10000;
     *          int var3 = false;
     *          System.out.println(var1);
     *       }
     *
     *    }
     */
    fun testLet() {
        user?.let {
            println(it)
        }
    }
}