# Quick_Recycler_View
A reusable recycler view that can be used for quick setup, adapt to different views and also search on different criteria.

[![](https://jitpack.io/v/sidcgithub/Experimental_Recycler_View.svg)](https://jitpack.io/#sidcgithub/Experimental_Recycler_View)

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
	        implementation 'com.github.sidcgithub:Experimental_Recycler_View:Tag'
	}
	
	
	
## Example use case
	
	call.enqueue(object : Callback<PopularMovies>{
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                if (response.isSuccessful){

                    //Testing out the recycler view with the api
                    response.body()?.results?.forEachIndexed { index, result ->
                        moviesArray.add(object : QuickItem {
                            // 1) Bind the text and images to the respective layout view ids
                            override var bindingMap: Map<String, Int> = mapOf("https://image.movieurl.org/all${result.poster_path}" to R.id.movie_photo,result.overview to R.id.movie_overview,result.title to R.id.movie_title, "Rating "+result.vote_average to R.id.movie_rating)
                            // 2) Bind object to correct item view layout
                            override var layoutId: Int = if(index%2==0) R.layout.item_layout else R.layout.item_layout2


                        })
                    }

                    SetupQuickRecycler.setupRecyclerView(
                        // 3) Supply context
                        this@MainActivity,
                        // 4) Supply the recycler view
                        testRecyclerView,
                        
                        // 5) Supply the behavior of the on click listener
                        object : OnQuickItemClickListener {
                            override fun onQuickItemClick(
                                item: View?,
                                position: Int,
                                itemView: View,
                                originalPosition: Int
                            ) {
                                Toast.makeText(this@MainActivity,response.body()?.results?.get(originalPosition)?.title,Toast.LENGTH_LONG).show()
                            }
                        }
                        ,
                        // 6) Supply the dataset
                        moviesArray)

                    searchEditText.addTextChangedListener(object: TextWatcher
                    {
                        override fun afterTextChanged(p0: Editable?) {

                        }

                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                        }

                        override fun onTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            // 7) Supply the views who's content is to be searched
                            (testRecyclerView.adapter as QuickAdapter).filter(query.toString(),R.id.movie_title,R.id.movie_overview)
                        }
                    })

                }
            }
            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {

            }
        })
	
