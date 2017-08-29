package com.wyl.dp.lsp;
/**
 * Created by wangyulin on 2/17/16.
 */

public class Clinet {

    public static void main(String[] args) {
        Soldier sanMao = new Soldier();
        sanMao.setGun(new MachineGun());
        sanMao.killEnemy();
    }
}
