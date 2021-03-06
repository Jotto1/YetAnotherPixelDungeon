/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Yet Another Pixel Dungeon
 * Copyright (C) 2015-2016 Considered Hamster
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.consideredhamster.yetanotherpixeldungeon.actors.mobs;

import com.consideredhamster.yetanotherpixeldungeon.Difficulties;
import com.watabou.utils.Random;
import com.consideredhamster.yetanotherpixeldungeon.Dungeon;

public abstract class MobPrecise extends Mob {

    protected MobPrecise(int exp) {
        this( Dungeon.chapter(), exp, false );
    }
    protected MobPrecise(int t, int exp, boolean isBoss) {

        tier = t;

        HT = 5 + tier * 4 + exp;
        armorClass = tier * 3;

        minDamage = tier + 1;
        maxDamage = tier * 4 + 1;

//        if( Dungeon.difficulty < Difficulties.NORMAL ) {
//            maxDamage -= tier;
//        } else if( Dungeon.difficulty > Difficulties.NORMAL ) {
//            minDamage += tier;
//        }

        // old values
        // 2-8     3-11    4-15    5-18    6-22

        // new values
        // 2-5     3-9     4-13    5-17    6-21

        accuracy = 3 + tier * 3 + exp;
        dexterity = 2 + tier * 2 + exp;

        if( !isBoss ) {

            if( Dungeon.difficulty == Difficulties.NORMAL ) {
                HT = Random.NormalIntRange(HT, HT * 2);
            } else if( Dungeon.difficulty > Difficulties.NORMAL ) {
                HT = HT * 2;
            }

            EXP = exp;
            maxLvl = exp + 5;

        } else {

            if( Dungeon.difficulty > Difficulties.HARDCORE ) {
                HT = HT * 15;
            } else {
                HT = HT * 8 + HT * 2 * Dungeon.difficulty;
            }

            EXP = exp * 5;
            maxLvl = 25;

            minDamage += tier - 1;
            maxDamage += tier - 1;

            dexterity /= 2;
            armorClass /= 2;

        }

        HP = HT;
    }

    @Override
    public float awareness(){
        return super.awareness() * ( 1.0f + tier * 0.05f );
    }

    @Override
    public float stealth(){
        return super.stealth() * ( 1.0f + tier * 0.05f );
    }
}
