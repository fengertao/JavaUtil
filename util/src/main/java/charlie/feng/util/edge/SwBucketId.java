package charlie.feng.util.edge;

/**
 * Created on 7/13/2016.
 */
public class SwBucketId {

    //if today is bucket 19, tomorrow will be bucket 20
    private static final int swType_90_72 = 90; //90 OR 72
    private static final int swGranularity = 1; //1,30 OR 5
//    private static String ts = "2016/7/11 15:23:41";

    public static void main(String[] args) {
//        System.out.println(converTs2Long());
        int bucketID = -99;
        long unixTimeInSeconds = 1479153970;
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

}
