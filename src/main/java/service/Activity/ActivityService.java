package service.Activity;

import model.Activity;

import java.util.List;

/**
 * Created by LulzimG on 29/12/16.
 */
public interface ActivityService {
    List<Activity> getAllActivities(String projectId);

    List<Activity> getMemberActivities(String memberId);

    List<Activity> getActivitiesForPandM(String projectId, String memberId);

    Activity getActivity(String activityId);

    String addActivity(Activity activity);

    String updateActivity(Activity activity);

    String deleteActivity(Activity activity);

    void insertBatch(List<Activity> activities);
}
