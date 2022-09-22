package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.sensors.SensorVelocityMeasPeriod;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;
import frc.robot.subsystems.drivers.Pigeon;

public class Drivetrain extends SubsystemBase {

    private static WPI_TalonSRX lDrive;
    private static WPI_TalonSRX rDrive;

    private static DifferentialDrive drive;

    public static Pigeon pigeon;

    public Drivetrain() {
        super();
        lDrive = new WPI_TalonSRX(RobotMap.LEFT_DRIVE);
        rDrive = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE);

        lDrive.setNeutralMode(NeutralMode.Brake);
        rDrive.setNeutralMode(NeutralMode.Brake);

        drive = new DifferentialDrive(lDrive, rDrive);

        drive.setSafetyEnabled(false); //safety for noob

        drive.setDeadband(0.04);

        lDrive.setSelectedSensorPosition(0);
        rDrive.setSelectedSensorPosition(0);

        lDrive.setInverted(false);
        rDrive.setInverted(false);

        lDrive.configVelocityMeasurementPeriod(SensorVelocityMeasPeriod.Period_1Ms);
        rDrive.configVelocityMeasurementPeriod(SensorVelocityMeasPeriod.Period_1Ms);

        lDrive.configVelocityMeasurementWindow(32);
        rDrive.configVelocityMeasurementWindow(32);

        pigeon = new Pigeon();

        pigeon.resetPigeonPosition();
    }

    public void drive(double speed, double rotation) {
        drive.arcadeDrive(speed, rotation);
    }

    public void setSpeed(double lPow, double rPow) {
        drive.tankDrive(lPow, rPow);
    }
    public void resetPigeon() {
        pigeon.resetPigeonPosition();
    }

    public double getLDriveSpd() {
        return lDrive.get();
    }

    public double getRDriveSpd() {
        return rDrive.get();
    }

    public double getLeftVelocity() {
        return lDrive.getSelectedSensorVelocity();
    }

    public double getRightVelocity() {
        return rDrive.getSelectedSensorVelocity();
    }
}
