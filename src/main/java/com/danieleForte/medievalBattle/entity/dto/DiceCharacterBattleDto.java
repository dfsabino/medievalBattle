package com.danieleForte.medievalBattle.entity.dto;

import com.danieleForte.medievalBattle.entity.BattleCharacter;
import com.danieleForte.medievalBattle.entity.HistoryPlayDice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiceCharacterBattleDto {

    private HistoryPlayDice dicePlay;
    private BattleCharacter battleCharacter;
}
