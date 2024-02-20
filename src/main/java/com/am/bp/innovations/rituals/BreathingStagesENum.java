package com.am.bp.innovations.rituals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public enum BreathingStagesENum implements Function<Integer, BreathingStagesENum> {
    I {

        @Override
        public BreathingStagesENum apply(Integer u) {
            commands.add("Lets Inhale for \n");
            for (int i = 0; i < Integer.valueOf(u); i++) {
                commands.add((i + 1) + "");
                commands.add(System.lineSeparator());
            }
            return this;
        }
    },
    H {
        @Override
        public BreathingStagesENum apply(Integer u) {
            commands.add("Now Hold\n");
            for (int i = 0; i < Integer.valueOf(u); i++) {
                commands.add((i + 1) + "");
                commands.add(System.lineSeparator());

            }
            return this;
        }
    },
    R {
        @Override
        public BreathingStagesENum apply(Integer u) {
            commands.add("Lets Release in\n");
            for (int i = 0; i < Integer.valueOf(u); i++) {
                commands.add((i + 1) + "");
                commands.add(System.lineSeparator());

            }
            return this;
        }
    };

    public static List<String> process(RitualLeg ritualLeg) {
        commands.clear();
        if (ritualLeg.getName().equalsIgnoreCase("SUMMITBREATHING")) {
            int equalizer = ritualLeg.getTimeinSeconds() / 3;
            BreathingStagesENum.I.apply(equalizer).H.apply(equalizer).I.apply(equalizer);
        }
        commands.add("Relax\n");
        commands.add("<silence msec=\"100\"/>\n");
        commands.add("Feel the calmness-\r\n");
        commands.add("<silence msec=\"1000\"/>");

        return commands;

    }

    private static List<String> commands = new ArrayList<>();

}
