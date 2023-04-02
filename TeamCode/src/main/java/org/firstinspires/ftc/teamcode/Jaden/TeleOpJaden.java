package org.firstinspires.ftc.teamcode.Jaden;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TeleOpJaden extends LinearOpMode {

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
    }
}