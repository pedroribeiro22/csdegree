import java.util.*;

public class FBFeedMap {

    Map <String, List<FBPost>> shelf;
    /**
     * Alínea a
     */
    public void addPost(String user, FBPost post) {
        for(FBPost post : this.shelf.values()) {
            String user = post.getUser();
            if(!this.shelf.containsKey(user)) {
                this.shelf.put(post, new ArrayList<FBPost>);
            }
        }
    }


}
