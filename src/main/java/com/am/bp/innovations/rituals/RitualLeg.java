package com.am.bp.innovations.rituals;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class RitualLeg {
    // NAMECHANTING|10S|3R|Total30SEC
    private String name;
    private int timeinSeconds;
    private int repitions;

    public static RitualLeg parse(String text) throws Exception {
        RitualLeg ritualLeg = new RitualLeg();
        if (!StringUtils.isEmpty(text)) {
            String textArray[] = text.split(SYMBOLS.PIPE.getRegexEqual());
            try {
                ritualLeg.setName(textArray[0]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                log.error("Invalid format: missing name");
                throw ex;
            }
            try {
                ritualLeg.setTimeinSeconds(Integer.valueOf(textArray[1]));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                log.error("Invalid format: invalid or missing time");
                throw ex;
            }
            try {
                ritualLeg.setRepitions(Integer.valueOf(textArray[2]));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                log.error("Invalid format: invalid or missing repitions");
                throw ex;
            }
        } else {
            throw new InvalidDatatypeValueException("Field Cannot be empty", null);
        }
        return ritualLeg;
    }

    public static String generateStringForT2SEngine(RitualLeg ritualLeg) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int reps = 0; reps < ritualLeg.getRepitions(); reps++) {
            if (ritualLeg.getName().equalsIgnoreCase("SUMMITBREATHING")) {

                List<String> commands = BreathingStagesENum.process(ritualLeg);
                // Strings.join(commands, );

            } else {
                stringBuilder.append(ritualLeg.getName() + "\n\r");
                stringBuilder.append("Rep  " + (reps + 1) + "\n\r");
                for (int seconds = 0; seconds <= ritualLeg.getTimeinSeconds(); seconds++) {
                    stringBuilder.append((seconds + 1) + "\n\r");
                }
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) throws Exception {
        RitualLeg leg = RitualLeg.parse("SUMMITBREATHING|15|3");
        String text = RitualLeg.generateStringForT2SEngine(leg);
        System.out.println(text);
    }

}
