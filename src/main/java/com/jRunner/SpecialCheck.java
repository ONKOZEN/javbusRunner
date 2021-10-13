package com.jRunner;

import java.io.File;

public class SpecialCheck {
    public static void main(String[] args) {
        String path = "H:\\备份 温浩然\\G\\F盘\\113013_707 uncensored.rmvb";
        File file = new File(path);
        String[] sp1 = file.getName().split(" ");
        String newName = GetName.getMidNewName(GetName.getUrl(sp1[0]))+"."+GetName.getExt(file);
        String extDisc = "超攻擊奇跡身體のG-Cup軟乳 超極上極品美乳美女愛乃なみNami";
        File newFile = new File(path + "\\" + newName);
        file.renameTo(newFile);
        System.out.println(newName);
    }
}
