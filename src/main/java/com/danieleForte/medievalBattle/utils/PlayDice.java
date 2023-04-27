package com.danieleForte.medievalBattle.utils;

import com.danieleForte.medievalBattle.entity.HistoryPlayDice;

import java.util.ArrayList;
import java.util.Random;

public class PlayDice {
    public HistoryPlayDice getDiceResult(int faceDice, int qtdDice)
    {
        Random gerador = new Random();
        HistoryPlayDice hDice = new HistoryPlayDice();
        ArrayList<Integer> dices = new ArrayList<Integer>();
        int sum=0;

        for(int i = 0;i<qtdDice;i++)
        {
            dices.add(gerador.nextInt(faceDice));
        }

        for (Integer num : dices) {
            sum += num;
        }

        hDice.setSumDices(sum);

        System.out.println("Dices:" + hDice.getSumDices());
        hDice.setDices(dices);

        System.out.println("Dices array:" +hDice.getDices());
        return hDice;
    }


}
