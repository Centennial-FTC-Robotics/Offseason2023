package org.firstinspires.ftc.teamcode.Joel;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
public class JoelRRTestOpmode extends LinearOpMode {

    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(0, 0, 0);

        drive.setPoseEstimate(startPose);

        TrajectorySequence traj = drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
                .lineToSplineHeading(new Pose2d(48, 0, Math.toRadians(90)))
                .lineToSplineHeading(new Pose2d(48, 48, Math.toRadians(180)))
                .lineToSplineHeading(new Pose2d(0, 48, Math.toRadians(270)))
                .lineToSplineHeading(new Pose2d(0, 0, 0))
                .build();


        waitForStart();

        drive.followTrajectorySequence(traj);

    }


}
