<img align="right" src="https://github.com/ada-school/module-template/blob/main/ada.png">


## Creating Lists on Android with RecyclerView

Implement a recurrent elements's list on Android using RecyclerView.

**Learning Objectives**

- [ ]  Use RecyclerView to implement a list on Android.
- [ ] Connect the UI with the backend using the ViewModel.



## Self Learning 🤹🏽 

Use the sample code and documentation guide to learn the basics of how to display lists on Android using RecyclerView.

**Main Topics**

* RecyclerView
* Adapter
* ViewHolder
* LayoutManager



## Codelab 🧪

🗣️ "I hear and I forget I see and I remember I do and I understand." Confucius



### Part 1: Implementing the RecyclerView:
1. Create the XML Layout that will display the dog breed information (name and photo)
2. Implement the Adapter to display the Dog's information:
  ```kotlin
      class DogsListAdapter(private val dataSet: List<Breed>) :
          RecyclerView.Adapter<DogsListAdapter.ViewHolder>() {

          /**
           * Provide a reference to the type of views that you are using
           * (custom ViewHolder).
           */
          class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
              val name: TextView
              val photo: ImageView

              init {
                  // Define click listener for the ViewHolder's View.
                  name = view.findViewById(R.id.name)
                  photo = view.findViewById(R.id.photo)
              }
          }

          // Create new views (invoked by the layout manager)
          override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
              // Create a new view, which defines the UI of the list item
              val view = LayoutInflater.from(viewGroup.context)
                  .inflate(R.layout.dog_row_view, viewGroup, false)

              return ViewHolder(view)
          }

          // Replace the contents of a view (invoked by the layout manager)
          override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

              // Get element from your dataset at this position and replace the
              // contents of the view with that element
              val breed = dataSet[position]
              viewHolder.name.text = dog.name

              if (breed.photoUrl.isNotEmpty())
                  Picasso.get().load(breed.photoUrl).into(viewHolder.photo);

          }

          // Return the size of your dataset (invoked by the layout manager)
          override fun getItemCount() = dataSet.size

      }
  ```
3. Create new Fragment called *DogsListFragment* with the following layout structure:
   ````xml
   <?xml version="1.0" encoding="utf-8"?>
      <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".ui.DogsListFragment">

      <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

      </androidx.constraintlayout.widget.ConstraintLayout>
   ````
4. Implement a ViewModel to inyect your services and add it to the *DogsListFragment*
    ````kotlin
      class DogsListFragment : Fragment() {

          val viewModel: DogsListFragmentViewModel by activityViewModels()
      
      }
      
      @HiltViewModel
      class DogsListFragmentViewModel
      @Inject constructor(
          private val breedDao: BreedDao,
          private val dogsImageService: DogsImageService
      ) : ViewModel() {
          
    ````
5. Implement the following function inside the *DogListFragment* to configure the *RecyclerView* and connect it with the LiveData Obervable that returns the dogs list:
  ````kotlin
      override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()
      }

      private fun configureRecyclerView() {
          binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
          viewModel.dogsListLiveData.observe(viewLifecycleOwner) {
                  dogsList ->
              binding.recyclerView.adapter = DogsListAdapter(dogsList)
          }

      }
  
  ````

### Part 2: Dog details View:
1. Implement an interface that will return wich Dog was clicked from the RecyclerView
  ````kotlin
    interface DogBreedClickListener {
       fun onDogBreedClicked(breed: Breed)
    }
  ````
2. Add a click listener inside the *DogsListAdapter* so you can know which Dog Breed was selected: 
  ````kotlin
     class DogsListAdapter(private val dataSet: List<Breed>, private val clickListener: DogBreedClickListener) :
        RecyclerView.Adapter<DogsListAdapter.ViewHolder>() {

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView
            val photo: ImageView

            init {
                // Define click listener for the ViewHolder's View.
                name = view.findViewById(R.id.name)
                photo = view.findViewById(R.id.photo)
            }
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.dog_row_view, viewGroup, false)

            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            val breed = dataSet[position]
            viewHolder.name.text = breed.name

            viewHolder.itemView.setOnClickListener { clickListener.onDogBreedClicked(breed) }

            if (breed.photoUrl.isNotEmpty())
                Picasso.get().load(breed.photoUrl).into(viewHolder.photo);

        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet.size

    }  
  ````
3. Make your *DogsListFragment* implment the *DogBreedClickListener* interface and store the selected breed into your shared ViewModel.
4. Create a new fragment called *BreedDetailsFragment* that displays the breed information and the variants if any.
5. Navigate to the *BreedDetailsFragment* inside the click listener function.

### Advance Challenge: Improved Dog App

1. Find another source where you can get more information about the Dogs' Breed and add this to your data model and to the Dog details view.

   ***Hint***: Use the [Wikipedia API](https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro&explaintext&redirects=1&titles=Affenpinscher) and the Wikipedia article about [List of Dog Breeds](https://www.wikiwand.com/en/List_of_dog_breeds)
