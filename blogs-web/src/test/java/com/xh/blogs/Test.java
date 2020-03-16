package com.xh.blogs;

import com.xh.blogs.domain.vo.AccountVo;
import com.xh.blogs.utils.BeanValidator;

/**
 * @Name Test
 * @Description
 * @Author wen
 * @Date 2019-07-26
 */
public class Test extends Thread{

    private Object o1;
    private Object o2;

    public Test(Object o1, Object o2){
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void run() {
        synchronized (o1){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("01...");
            synchronized(o2){
                System.err.println("02...");
            }
        }
    }

    public static void main(String[] args) {
        AccountVo accountVo = new AccountVo();
        BeanValidator.check(accountVo);
//        Object o1 = new Object();
//        Object o2 = new Object();
//        new Test(o1, o2).start();
//        new Test(o2, o1).start();
    }
}
