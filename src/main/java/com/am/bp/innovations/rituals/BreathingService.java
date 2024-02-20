package com.am.bp.innovations.rituals;

public class BreathingService {

    public static void main(String[] args) throws Exception {
        String leg = "SUMMITBREATHING|15|3";
        BreathingStagesENum.process(RitualLeg.parse(leg));
    }
}
