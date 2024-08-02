package org.iproute.search;

import java.io.File;

/**
 * Created by winterfell on 2017/10/11.
 */
public class FileOperator {

    // 练习递归
    public static void listFile(String srcPath){
        listFile(new File(srcPath),"");
    }

    private static void listFile(File file,String level){
        if(file.exists()){
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for(File tmp : files){
                    if(tmp.isDirectory()){
                        System.out.println(level+"FOLDER:"+tmp.getName());
                    }
                    listFile(tmp,level+"\t");

                }
            }else if(file.isFile()){
                System.out.println(level+"FILE:"+file.getName());
            }
        }
    }


    public static void main(String[] args) {

//        File file = new File("/Users/winterfell/code/source");
//        System.out.println(file.exists());
//        System.out.println(System.getProperty("user.dir"));

        listFile("/Users/winterfell");
    }


}
