package com.danieleForte.medievalBattle.model;

import com.danieleForte.medievalBattle.entity.Character;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BattleCharacterModel {

    private String code_share;
    private String name;
    private long characterId;
}
