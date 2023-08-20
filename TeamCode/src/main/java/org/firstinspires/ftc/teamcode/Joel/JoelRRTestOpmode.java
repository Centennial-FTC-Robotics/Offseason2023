package org.firstinspires.ftc.teamcode.Joel;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class JoelRRTestOpmode extends LinearOpMode {

    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Trajectory traj = drive.trajectoryBuilder(new Pose2d(0, 0, 0))
                .splineTo(new Vector2d(0, 36), 0)
                .splineTo(new Vector2d(36, 36), 0)
                .splineTo(new Vector2d(36, 0), 0)
                .splineTo(new Vector2d(0, 0), 0)
                .build();


        waitForStart();

        drive.followTrajectory(traj);

    }


}
