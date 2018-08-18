package com.unciv.logic.battle

import com.unciv.logic.civilization.CivilizationInfo
import com.unciv.logic.map.MapUnit
import com.unciv.logic.map.TileInfo
import com.unciv.models.gamebasics.unit.UnitType

class MapUnitCombatant(val unit: MapUnit) : ICombatant {
    override fun getHealth(): Int = unit.health
    override fun getCivilization(): CivilizationInfo = unit.civInfo
    override fun getTile(): TileInfo = unit.getTile()
    override fun getName(): String = unit.name
    override fun isDefeated(): Boolean = unit.health <= 0

    override fun takeDamage(damage: Int) {
        unit.health -= damage
        if(isDefeated()) unit.removeFromTile()
    }

    override fun getAttackingStrength(defender: ICombatant): Int { // todo remove defender
        if (isRanged()) return unit.getBaseUnit().rangedStrength
        else return unit.getBaseUnit().strength
    }

    override fun getDefendingStrength(attacker: ICombatant): Int {  // todo remove attacker
        return unit.getBaseUnit().strength
    }

    override fun getUnitType(): UnitType {
        return unit.getBaseUnit().unitType
    }

    override fun toString(): String {
        return unit.name+" of "+unit.civInfo.civName
    }
}