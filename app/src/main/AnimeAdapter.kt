class AnimeAdapter(private val onItemClick: (id: String) -> Unit) :
    PagingDataAdapter<DataItem, AnimeAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    onItemClick(it.id)
                }
            }
        }

        fun onBind(animeData: DataItem) = with(binding) {
            Glide.with(ivImage.context)
                .load(animeData.attributes.posterImage.original)
                .into(ivImage)
            titleTextView.text = animeData.attributes.titles.en

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAnimeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
}