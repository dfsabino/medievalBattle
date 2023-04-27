package com.danieleForte.medievalBattle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryPlayDice {

    private ArrayList dices;

    private int sumDices;
}
