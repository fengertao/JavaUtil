package charlie.feng.util.edge;

/**
 * Created on 7/13/2016.
 */
public class SwBucketId {

    //the long string copy from database "Last_updated" field
    private static long unixTimeInSeconds = 1479153970;   //The time updated field of Edge database, in second instead of minisecond.
    //if today is bucket 19, tomorrow will be bucket 20
    private static int swType_90_72 = 90; //90 OR 72
    private static int swGranularity = 1; //1,30 OR 5
//    private static String ts = "2016/7/11 15:23:41";

    public static void main(String[] args) {
//        System.out.println(converTs2Long());
        int bucketID = -99;
//        System.out.println(getGranularityDurationInSeconds());
//        System.out.println(getNumberOfBucket());
        bucketID = (int) Math.floor(unixTimeInSeconds / getGranularityDurationInSeconds()) % getNumberOfBucket();
        System.out.println("BuckedId ===> " + bucketID);
    }

    private static int getGranularityDurationInSeconds() {
        return swType_90_72 == 90 ?
                (swGranularity == 1 ? 24 * 60 * 60 : (swGranularity == 30 ? 30 * 60 : (swGranularity == 5 ? 5 * 60 : 0))) :
                ((swGranularity == 1 ? 60 * 60 : (swGranularity == 30 ? 30 * 60 : (swGranularity == 5 ? 5 * 60 : 0))));
    }

    private static int getNumberOfBucket() {
        return swGranularity == 1 ? (swType_90_72 == 90 ? 90 : 72) : (swGranularity == 30 ? 48 : 12);
    }

//    private static long converTs2Long() {
//        //DateFormat df = DateFormat.getDateInstance();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        try {
//            Date date = df.parse(ts);
//            //TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
//            Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
//            //Calendar cal2 = Calendar.getInstance();
//            cal2.setTimeInMillis(1465790867000L - 8 * 60 * 60 * 1000);
//            System.out.println(cal2.getTime());
//            return date.getTime() / 1000;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return 1;
//    }

}
