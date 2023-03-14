// package frc.robot.Subsystems;

// import edu.wpi.first.math.filter.MedianFilter;
// import edu.wpi.first.wpilibj.Counter;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class LidarSensor extends SubsystemBase {
//         MedianFilter filter = new MedianFilter(10);
//         private Counter counter;
//         private static final double offset = 0.0;
//         private static final double factor = 100000;
//             /* Based on your sensor, you will have different data.
//      * Use multiple data points and a linear regression program
//      * to obtain personal factor and offset values.
//      * for cm: 100000, for in: 39400 (from our testing)
//     */

//         public void initlidarSensor() {
//             counter = new Counter(7); //port it uses
//             counter.setMaxPeriod(1.0);
//             counter.setSemiPeriodMode(true);
//             counter.reset();
//         }

//         public double getDistance() {
//             if (counter.get() < 1) {
//                 return 0.0;
//             }
//             return (counter.getPeriod()) * factor + offset; // Use lin reg to obtain values
//         }
//     }
