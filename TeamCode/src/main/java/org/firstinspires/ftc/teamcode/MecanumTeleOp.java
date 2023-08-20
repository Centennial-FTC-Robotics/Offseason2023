package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="MecanumTeleOp")
public class MecanumTeleOp extends LinearOpMode {

    //Initialize drive motor variables
    //FL = Front Left, FR = Front Right, BL = Back Left, BR = Back Right
    public DcMotor FLmotor;
    public DcMotor FRmotor;
    public DcMotor BLmotor;
    public DcMotor BRmotor;

    @Override
    public void runOpMode() {
        //Map names on phone's robot configuration with motor variables we initialized
        FLmotor = hardwareMap.get(DcMotor.class, "frontLeft");
        FRmotor = hardwareMap.get(DcMotor.class, "frontRight");
        BLmotor = hardwareMap.get(DcMotor.class, "backLeft");
        BRmotor = hardwareMap.get(DcMotor.class, "backRight");

        FLmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FRmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BLmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BRmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        FLmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        FRmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BLmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BRmotor.setDirection(DcMotorSimple.Direction.FORWARD);

        BRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            //Map gamepad values to variables
            double drive = gamepad1.left_stick_y;
            double strafe = -gamepad1.left_stick_x;
            double turn = -gamepad1.right_stick_x;

            //Do some simple math to set power for motors
            double FLPower = Range.clip(drive + strafe + turn, -1.0, 1.0);
            double FRPower = Range.clip(drive - strafe - turn, -1.0, 1.0);
            double BLPower = Range.clip(drive - strafe + turn, -1.0, 1.0);
            double BRPower = Range.clip(drive + strafe - turn, -1.0, 1.0);

            FLmotor.getCurrentPosition();

            //Set power
            FLmotor.setPower(FLPower);
            FRmotor.setPower(FRPower);
            BLmotor.setPower(BLPower);
            BRmotor.setPower(BRPower);

            telemetry.addData("Left Encoder", BRmotor.getCurrentPosition());
            telemetry.addData("Right Encoder", FLmotor.getCurrentPosition());
            telemetry.addData("Horizontal Encoder", BLmotor.getCurrentPosition());
            telemetry.update();
        }
    }
}
