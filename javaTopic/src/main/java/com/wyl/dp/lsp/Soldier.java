package com.wyl.dp.lsp;

/**
 * Created by wangyulin on 2/17/16.
 */
public class Soldier {
    private AbstractGun _gun;

    public void setGun(AbstractGun gun) {
        this._gun = gun;
    }

    public void killEnemy(){
        System.out.println("士兵开始杀人...");
        _gun.shoot();
    }
}
