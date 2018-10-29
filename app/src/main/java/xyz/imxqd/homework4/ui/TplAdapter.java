package xyz.imxqd.homework4.ui;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import xyz.imxqd.homework4.R;
import xyz.imxqd.homework4.net.model.Template;

public class TplAdapter extends RecyclerView.Adapter<TplAdapter.ViewHolder> {

    public static final int VIEW_TYPE_FOOTER = 0;
    public static final int VIEW_TYPE_RECOMMEND = 1;
    public static final int VIEW_TYPE_ON_SALE = 2;
    public static final int VIEW_TYPE_LARGE_IMAGE = 3;
    public static final int VIEW_TYPE_ENTRY = 4;
    public static final int VIEW_TYPE_BANNER = 5;
    public static final int VIEW_TYPE_UNKNOWN = 100;


    private final List<Template> mValues;

    public TplAdapter() {
        mValues = new ArrayList<>();
    }

    public void clear() {
        mValues.clear();
    }

    public void addAll(List<Template> list) {
        mValues.addAll(list);
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_TYPE_RECOMMEND:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tpl_recommend, parent, false);
                return new RecommendHolder(view);
            case VIEW_TYPE_BANNER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tpl_banner, parent, false);
                return new BannerHolder(view);
            case VIEW_TYPE_ENTRY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tpl_entry, parent, false);
                return new EntryHolder(view);
            case VIEW_TYPE_LARGE_IMAGE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tpl_large_image, parent, false);
                return new LargeImageHolder(view);
            case VIEW_TYPE_ON_SALE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tpl_on_sale, parent, false);
                return new OnSaleHolder(view);
            case VIEW_TYPE_FOOTER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tpl_footer, parent, false);
                return new ViewHolder(view);
            default:
                view = new View(parent.getContext());
                return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (position < mValues.size()) {
            Template template = mValues.get(position);
            holder.bindItem(template);
        } else {
            holder.bindItem(null);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mValues.size()) {
            return VIEW_TYPE_FOOTER;
        }
        switch (mValues.get(position).templateType) {
            case Template.TYPE_RECOMMEND:
                return VIEW_TYPE_RECOMMEND;
            case Template.TYPE_BANNER:
                return VIEW_TYPE_BANNER;
            case Template.TYPE_ENTRY:
                return VIEW_TYPE_ENTRY;
            case Template.TYPE_ON_SALE:
                return VIEW_TYPE_ON_SALE;
            case Template.TYPE_LARGE_IMAGE:
                return VIEW_TYPE_LARGE_IMAGE;
        }
        return VIEW_TYPE_UNKNOWN;
    }

    @Override
    public int getItemCount() {
        return mValues.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Template mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        }

        public void bindItem(Template item) {
            mItem = item;
        }
    }


    public class EntryHolder extends ViewHolder {

        public List<TextView> titles;
        public List<ImageView> images;

        public EntryHolder(View view) {
            super(view);
            titles = new ArrayList<>(5);
            images = new ArrayList<>(5);
            titles.add((TextView) view.findViewById(R.id.item1));
            titles.add((TextView) view.findViewById(R.id.item2));
            titles.add((TextView) view.findViewById(R.id.item3));
            titles.add((TextView) view.findViewById(R.id.item4));
            titles.add((TextView) view.findViewById(R.id.item5));

            images.add((ImageView) view.findViewById(R.id.image1));
            images.add((ImageView) view.findViewById(R.id.image2));
            images.add((ImageView) view.findViewById(R.id.image3));
            images.add((ImageView) view.findViewById(R.id.image4));
            images.add((ImageView) view.findViewById(R.id.image5));
        }

        @Override
        public void bindItem(Template item) {
            super.bindItem(item);
            List<Template.Item> items = item.items;
            if (items != null && items.size() > 0) {
                mView.setVisibility(View.VISIBLE);
                for (int i = 0; i < titles.size(); i++) {
                    if (items.size() > i) {
                        titles.get(i).setVisibility(View.VISIBLE);
                        Glide.with(titles.get(i))
                                .load(items.get(i).itemImage)
                                .into(images.get(i));
                        titles.get(i).setText(Html.fromHtml(items.get(i).itemTitle));

                    } else {
                        titles.get(i).setVisibility(View.GONE);
                    }
                }
            } else {
                mView.setVisibility(View.GONE);
            }
        }
    }


    public class RecommendHolder extends ViewHolder{
        public List<ImageView> images;

        public RecommendHolder(View view) {
            super(view);
            images = new ArrayList<>(4);
            images.add((ImageView) view.findViewById(R.id.image1));
            images.add((ImageView) view.findViewById(R.id.image2));
            images.add((ImageView) view.findViewById(R.id.image3));
            images.add((ImageView) view.findViewById(R.id.image4));
        }

        @Override
        public void bindItem(Template item) {
            super.bindItem(item);
            List<Template.Item> items = item.items;
            if (items != null && items.size() > 0) {
                mView.setVisibility(View.VISIBLE);
                for (int i = 0; i < images.size(); i++) {
                    if (items.size() > i) {
                        images.get(i).setVisibility(View.VISIBLE);
                        Glide.with(images.get(i))
                                .load(items.get(i).itemImage)
                                .into(images.get(i));
                    } else {
                        images.get(i).setVisibility(View.GONE);
                    }
                }
            } else {
                mView.setVisibility(View.GONE);
            }
        }
    }

    public class BannerHolder extends ViewHolder {
        Banner banner;
        public BannerHolder(View view) {
            super(view);
            banner = view.findViewById(R.id.banner);
        }

        @Override
        public void bindItem(Template item) {
            super.bindItem(item);
            if (item != null && item.items != null && item.items.size() > 0) {
                mView.setVisibility(View.VISIBLE);
                List<String> urls = new ArrayList<>();
                for (Template.Item i : item.items) {
                    urls.add(i.itemImage);
                }
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(imageView).load(path).into(imageView);
                    }
                });
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                banner.setImages(urls);
                banner.start();
            } else {
                mView.setVisibility(View.GONE);
            }
        }
    }

    public class LargeImageHolder extends ViewHolder {

        ImageView imageView;
        public LargeImageHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
        }

        @Override
        public void bindItem(Template item) {
            super.bindItem(item);
            if (item != null && item.items != null && item.items.size() > 0) {
                mView.setVisibility(View.VISIBLE);
                Glide.with(imageView).load(item.items.get(0).itemImage).into(imageView);
            } else {
                mView.setVisibility(View.GONE);
            }
        }
    }

    public class OnSaleHolder extends ViewHolder {

        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;

        public OnSaleHolder(View view) {
            super(view);
            recyclerView = view.findViewById(R.id.list);
            adapter = new RecyclerView.Adapter<SaleItemHolder>() {

                @NonNull
                @Override
                public SaleItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_tpl_on_sale_item, viewGroup, false);
                    return new SaleItemHolder(view);
                }

                @Override
                public void onBindViewHolder(@NonNull SaleItemHolder viewHolder, int i) {
                    viewHolder.bindData(mItem.items.get(i));
                }

                @Override
                public int getItemCount() {
                    return mItem.items.size();
                }
            };

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        }

        public class SaleItemHolder extends RecyclerView.ViewHolder {

            ImageView image;
            TextView title;
            TextView price;


            public SaleItemHolder(@NonNull View view) {
                super(view);
                image = view.findViewById(R.id.image);
                title = view.findViewById(R.id.title);
                price =  view.findViewById(R.id.price);
            }

            public void bindData(Template.Item item) {
                Glide.with(image).load(item.itemImage).into(image);
                title.setText(Html.fromHtml(item.itemTitle));
                price.setText("￥" + Integer.valueOf(item.itemPrice) / 1000f);
            }
        }
    }
}
