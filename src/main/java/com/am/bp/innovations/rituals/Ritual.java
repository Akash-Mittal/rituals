package com.am.bp.innovations.rituals;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Ritual {
    private List<RitualLeg> ritualLegList;

    public static Ritual parse(String text) throws Exception {
        String textArray[] = text.split(SYMBOLS.COMMA.getRegexEqual());
        List<RitualLeg> list = new ArrayList<>();
        for (int i = 0; i < textArray.length; i++) {
            try {
                if (!StringUtils.isEmpty(textArray[i])) {
                    list.add(RitualLeg.parse(textArray[i].trim()));
                }
            } catch (Exception e) {
                throw e;
            }
        }
        Ritual ritual = new Ritual();
        ritual.setRitualLegList(list);
        return ritual;
    }

    public static String getRitualLegNames(Ritual ritual) {
        String name = "";
        for (int i = 0; i < ritual.getRitualLegList().size(); i++) {
            name = name + " " + ritual.getRitualLegList().get(i).getName();
        }
        return name;
    }

    public static String generateStringForT2SEngine(Ritual ritual) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Your Ritual Starts Now \r\n");
        for (int reps = 0; reps < ritual.getRitualLegList().size(); reps++) {
            RitualLeg ritualLeg = ritual.getRitualLegList().get(reps);
            stringBuilder.append(RitualLeg.generateStringForT2SEngine(ritualLeg));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws Exception {
        Ritual leg = Ritual.parse("SUMMITBREATHING|15|3,NAMECHANTING|10|3");
        String text = Ritual.generateStringForT2SEngine(leg);
        System.out.println(text);
    }

}
