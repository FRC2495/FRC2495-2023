// compliment of team1675

package frc.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class DPadButton extends Trigger {

    Joystick joystick;
    Direction direction;

    public DPadButton(Joystick joystick, Direction direction) {
        this.joystick = joystick;
        this.direction = direction;
    }

    public static enum Direction {
        UP(0), RIGHT(90), DOWN(180), LEFT(270);

        int direction;

        private Direction(int direction) {
            this.direction = direction;
        }
    }

    @Override
    public boolean getAsBoolean() {
        int dPadValue = joystick.getPOV();
        return (dPadValue == direction.direction) || (dPadValue == (direction.direction + 45) % 360)
                || (dPadValue == (direction.direction + 315) % 360);
    }

}