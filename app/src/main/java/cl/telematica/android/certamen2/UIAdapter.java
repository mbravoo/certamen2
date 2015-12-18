package cl.telematica.android.certamen2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * Created by Matias Gilbert on 18-12-2015.
 */
public class UIAdapter extends RecyclerView.Adapter<UIAdapter.ViewHolder> {
    private JSONArray mData;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mIDView;
        public TextView mJokeView;

        public ViewHolder(View v) {
            super(v);
            mIDView = (TextView) v.findViewById(R.id.textID);
            mJokeView = (TextView) v.findViewById(R.id.textJoke);
        }
    }

    public UIAdapter(JSONArray list) {
        mData = list;
    }

    @Override
    public UIAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            holder.mIDView.setText("ID: " + mData.getJSONObject(position).getString("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            holder.mJokeView.setText(mData.getJSONObject(position).getString("joke"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mData.length();
    }
}
