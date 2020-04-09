package autoxwatchdog.commander;

//Not implemented: Featured did not meet time constraints.
//For future implementation (Cavan Biggs April 8th 2020)

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

//import com.amazonaws.mobile.client.AWSMobileClient;
//import com.amazonaws.mobile.client.Callback;
//import com.amazonaws.mobile.client.UserStateDetails;
//import com.amplifyframework.api.aws.AWSApiPlugin;
//import com.amplifyframework.api.graphql.GraphQLResponse;
//import com.amplifyframework.api.graphql.MutationType;
//import com.amplifyframework.core.Amplify;
//import com.amplifyframework.core.ResultListener;
//import com.amplifyframework.datastore.generated.model.Blog;
//import com.amplifyframework.datastore.generated.model.Post;

public class AmazonS3_Demo extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_amazon_s3__demo);
//
//        AWSMobileClient.getInstance().initialize(getApplicationContext(), new Callback<UserStateDetails>(){
//            @Override
//            public void onResult(UserStateDetails userStateDetails){
//                try {
//                    Amplify.addPlugin(new AWSApiPlugin());
//                    Amplify.configure(getApplicationContext());
//                    Log.i("ApiQuickstart", "All set and ready to go!");
//                } catch(Exception e){
//                    Log.e("ApiQuickstart", e.getMessage());
//                }
//            }
//
//            @Override
//            public void onError(Exception e){
//                Log.e("ApiQuckstart", "Initialization error.", e);
//            }
//        });
//    }
//
//    //Create blog
//    private void createBlog(){
//        Blog blog = Blog.builder().name("My first blog").build();
//
//        Amplify.API.mutate(
//                blog,
//                MutationType.CREATE,
//                new ResultListener<GraphQLResponse<Blog>>() {
//                    @Override
//                    public void onResult(GraphQLResponse<Blog> response) {
//                        Log.i("ApiQuickStart", "Added Blog with id: " + response.getData().getId());
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        Log.e("ApiQuickStart", throwable.getMessage());
//
//                    }
//                }
//        );
//    }
//
//    //Create blog post
//    private void createPost(String postName, String blogId){
//        Post post = Post.builder().title(postName).blog(Blog.justId(blogId)).build();
//
//        Amplify.API.mutate(
//                post,
//                MutationType.CREATE,
//                new ResultListener<GraphQLResponse<Post>>() {
//                    @Override
//                    public void onResult(GraphQLResponse<Post> response) {
//                        Log.i("ApiQuickStart", "Added Post " + response.getData().getId());
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        Log.e("ApiQuickStart", throwable.getMessage());
//
//                    }
//                }
//        );
//
//    }
}
