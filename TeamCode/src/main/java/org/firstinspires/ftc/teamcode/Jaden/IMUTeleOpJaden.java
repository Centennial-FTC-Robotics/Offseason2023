package org.firstinspires.ftc.teamcode.Jaden;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class IMUTeleOpJaden extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    private BNO055IMU imu;

    public void initialize(LinearOpMode opMode) {
        frontLeft = opMode.hardwareMap.dcMotor.get("frontLeft");
        frontRight = opMode.hardwareMap.dcMotor.get("frontRight");
        backLeft = opMode.hardwareMap.dcMotor.get("backLeft");
        backRight = opMode.hardwareMap.dcMotor.get("backRight");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        imu = opMode.hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu.initialize(parameters);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialize(this);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            double drive = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;

            double angle = imu.getAngularOrientation().firstAngle;
            double rot_x = strafe * Math.cos(Math.toRadians(angle)) - drive * Math.sin(Math.toRadians(angle));
            double rot_y = strafe * Math.sin(Math.toRadians(angle)) + drive * Math.cos(Math.toRadians(angle));
            telemetry.addData("rot_x", rot_x);
            telemetry.addData("rot_y", rot_y);
            telemetry.addData("firstAngle", angle);
            telemetry.addData("secondAngle", imu.getAngularOrientation().secondAngle);
            telemetry.addData("thirdAngle", imu.getAngularOrientation().thirdAngle);
            telemetry.update();
            double frontLeftPower = rot_y+turn+rot_x;
            double frontRightPower = rot_y-turn-rot_x;
            double backLeftPower = rot_y+turn-rot_x;
            double backRightPower = rot_y-turn+rot_x;

            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);
        }
    }
}
