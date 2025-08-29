package com.example.foodapp


object RestaurantData {
    fun getAllData() : List<Restaurant>{
        return listOf(

            Restaurant(
                title = "Coffee JOA",
                description = """
                                Coffee Joa is a Korean-Malaysian-owned cafe in Miri,Sarawak, 
                                known for its fusion of Korean and Western flavors, 
                                particularly its Korean-inspired desserts and drinks. 
                                The cafe is praised for its cozy ambiance and is a popular spot 
                                for food enthusiasts and those interested in Korean culture.
                            """.trimIndent(),
                phoneNumber = "‪+6016-961-0151‬",
                categories = listOf("Dessert"),
                price = "RM20-RM40",
                mealType = "Lunch",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "Coffee Joa, Lot 1996, Ground Floor, Marina Square 2, Marina Parkcity, Miri, Sarawak, Malaysia",
                mapsUrl = "https://maps.app.goo.gl/5MGkK1JkgsH9b2qc8",
                hours = mapOf(
                    "Monday" to "Closed",
                    "Tuesday" to "10 am–10 pm",
                    "Wednesday" to "10 am–10 pm",
                    "Thursday" to "10 am–10 pm",
                    "Friday" to "10 am–10 pm",
                    "Saturday" to "10 am–10 pm",
                    "Sunday" to "10 am–10 pm"),
                rating = 4.0f,        // Added this line
                priceLevel= 2

            ),

            Restaurant(
                title = "In the Mood Cafe",
                description = """
                    In The Mood Cafe is a cozy spot in Miri that's famous for its heavenly 
                    cakes and coffee. The cafe offers delectable desserts made with love by 
                    Nightowlbakes, including biscoff burnt cheesecake, baked matcha yoghurt 
                    cheesecake, and espresso baked cheesecake. In addition to an array of cakes, 
                    the cafe also serves matcha drinks and other tasty treats. Visitors can expect 
                    attentive service from the professional crew while enjoying a memorable and 
                    enjoyable F&B experience.
                """.trimIndent(),
                phoneNumber = "‪+6010-982 6537‬",
                categories = listOf("Dessert"),
                price = "RM20-RM40",
                mealType = "Lunch",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "Ground Floor, Lot 633, Jalan North Yu Seng, Utara, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/zJeV7oDYNrYCKc527",
                hours =mapOf(
                    "Monday" to "1015 am - 6 pm",
                    "Tuesday" to "1015 am - 6 pm",
                    "Wednesday" to "1015 am - 6 pm",
                    "Thursday" to "1015 am - 6 pm",
                    "Friday" to "1015 am - 6 pm",
                    "Saturday" to "1015 am - 6 pm",
                    "Sunday" to "1015  am - 6 pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "The Litt",
                description = """
                    If you’re looking for a cozy spot in Miri to unwind with a cup of coffee and a 
                    slice of cake, The Litt Miri is the place to be. This charming cafe offers a 
                    variety of delicious cakes, each one looking more tempting than the last. 
                    Whether you’re in the mood for something rich and decadent or light and fruity, 
                    there/'s a cake here to satisfy your craving.
                """.trimIndent(),
                phoneNumber = "‪+6011-3359 5257‬",
                categories = listOf("Dessert"),
                price = "RM20-RM40",
                mealType = "Lunch",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "Ground Floor, lot 2070, Jalan Brighton, Phase 1, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/hUobAf1pNpU7BzCRA",
                hours =mapOf(
                    "Monday" to "1030 am - 1030 pm",
                    "Tuesday" to "1030 am - 1030 pm",
                    "Wednesday" to "1030 am - 1030 pm",
                    "Thursday" to "1030 am - 1030 pm",
                    "Friday" to "1030 am - 1030 pm",
                    "Saturday" to "1030 am - 1030 pm",
                    "Sunday" to "1030 am - 1030 pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "United Coffee",
                description = """
                    United Coffee Roastery Cafe in Miri, Sarawak, is a popular spot for coffee 
                    lovers, offering a cozy atmosphere and a variety of coffee, cakes, and light 
                    meals. They are known for freshly baked goods like the Pistachio Roll, and 
                    savory options like the Salmon Delight Baguette and Cheesy Tuna Melt Ciabatta. 
                    They also roast their own coffee beans and serve unique drinks like the 
                    Dirty Latte and a special Single Origin Hot Chocolate, with a dairy-free option
                     using oat milk. 
                """.trimIndent(),
                phoneNumber = "+6011-2685 6698",
                categories = listOf("Dessert"),
                price = "RM20-RM40",
                mealType = "Lunch",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = " Lot 1862, Marina Phase 2, Miri City, Marina Parkcity, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/KC6pZ1E9BxV1oA6eA",
                hours =mapOf(
                    "Monday" to "0830 am - 5 pm",
                    "Tuesday" to "0830 am - 5 pm",
                    "Wednesday" to "0830 am - 5 pm",
                    "Thursday" to "0830 am - 5 pm",
                    "Friday" to "0830 am - 10 pm",
                    "Saturday" to "0830 am - 10 pm",
                    "Sunday" to "0830 am - 5 pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "Crème Studio",
                description = """
                    Crème Studio in Miri is a dessert shop located in Marina Phase 2, known for its 
                    sweet treats like crepes, cheesecakes, and coffee. They offer a variety of 
                    desserts, including a Banana Choco Crepecake, Tiramisu Burnt Cheesecake, and 
                    Iced Vienna Cafe Latte. The studio is praised for its balanced flavors and 
                    smooth textures, with a focus on quality ingredients and unique flavor 
                    combinations
                """.trimIndent(),
                phoneNumber = "+6012-2206160", // phone havent change
                categories = listOf("Dessert"),
                price = "RM20-RM40",
                mealType = "Lunch",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "Marina Phase 2, Miri.",
                mapsUrl = "https://share.google/OrN3GuLKGnPihqNX8",
                hours =mapOf(
                    "Monday" to "1030 am - 1030 pm",
                    "Tuesday" to "1030 am - 1030 pm",
                    "Wednesday" to "1030 am - 1030 pm",
                    "Thursday" to "1030 am - 1030 pm",
                    "Friday" to "1030 am - 1030 pm",
                    "Saturday" to "1030 am - 1030 pm",
                    "Sunday" to "1030 am - 1030 pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "1st Sip Slow Bar",
                description = """
                    Our hidden café isn’t just about drinks; it’s about creating an experience 
                    that lingers. It’s a place where coffee enthusiasts discover new flavors, 
                    where non-drinkers find sophisticated mocktail options, and where guests come 
                    not just for a beverage, but for a feeling of belonging. By focusing on quality, 
                    creativity, and atmosphere, the café offers something unique—a haven for those 
                    who appreciate the finer details of a drink, the interior combines warm, 
                    earthy tones with minimalist-designed space, and the joy of a quiet moment in 
                    an otherwise busy world.
                """.trimIndent(),
                phoneNumber = "+6011-6140 7910",
                categories = listOf("Dessert"),
                price = "RM20-RM40",
                mealType = "Lunch",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "Lot 577, 1st Floor (above QQ Beijing Roasted Duck), Pelita Commercial Centre, " +
                        "Miri\n" +
                        "(Opposite 5050 Café)",
                mapsUrl = "https://maps.app.goo.gl/44qdGmxutLe2uP3n6",
                hours =mapOf(
                    "Monday" to "12 pm - 6 pm ",
                    "Tuesday" to "12 pm - 6 pm ",
                    "Wednesday" to "12 pm - 6 pm ",
                    "Thursday" to "12 pm - 6 pm ",
                    "Friday" to "12 pm - 10 pm ",
                    "Saturday" to "12 pm - 10 pm ",
                    "Sunday" to "12 pm - 10 pm "
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "BonBon ",
                description = """
                    Bon Bon Patisserie, also known as Bon Bon Cafe, is a popular bakery and cafe in 
                    Miri, Sarawak, known for its pastries, cakes, and brunch options. It is 
                    particularly well-regarded for its French-style pastries, various buns, and a 
                    selection of cakes. Some reviews highlight the fresh pastries, lovely design, 
                    and the fact that some items, like buns, sell out quickly. The cafe has a 
                    minimalist, Scandinavian-inspired design. 
                """.trimIndent(),
                phoneNumber = "+6014-364 0822",
                categories = listOf("Dessert"),
                price = "RM20-RM40",
                mealType = "Lunch",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = " 596, Jalan Pelita 2, Pelita Commercial Centre, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/AWKnk8wyU9Jx3P886",
                hours =mapOf(
                    "Monday" to "10 am - 6 pm  ",
                    "Tuesday" to "10 am - 6 pm  ",
                    "Wednesday" to "Closed",
                    "Thursday" to "10 am - 6 pm  ",
                    "Friday" to "10 am - 6 pm  ",
                    "Saturday" to "10 am - 6 pm  ",
                    "Sunday" to "10 am - 6 pm  "
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "Blue Cottage Coffee ",
                description = """
                    Blue Cottage Coffee is a name synonymous to coffee love. It was our passion and 
                    love for coffee that allowed us to venture deeper into coffee excellence. 
                    We started off this exciting venture in 2016. By early 2019 we had introduced a 
                    plethora of different coffee-related products, including various aromatic 
                    roasted coffee beans, syrup and even the most efficient of coffee makers, 
                    machines, and tools. We also offer consultation to make sure you choose the 
                    perfect coffee machine for your requirements, as well as the required training.
                """.trimIndent(),
                phoneNumber = "+6011-6562 6166",
                categories = listOf("Dessert"),
                price = "RM20-RM40",
                mealType = "Lunch",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "  No.6, Ground Floor & M Floor, Lot 2357, Jalan Krokop 2, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/eeWDa8Vo9fGXHojNA",
                hours =mapOf(
                    "Monday" to "8 am - 5 pm",
                    "Tuesday" to "8 am - 5 pm",
                    "Wednesday" to "8 am - 5 pm",
                    "Thursday" to "8 am - 5 pm",
                    "Friday" to "8 am - 5 pm",
                    "Saturday" to "8 am - 5 pm",
                    "Sunday" to "Closed"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "QQ Dessert",
                description = """
                    QQ Dessert in Miri is a dessert shop known for its wide variety of sweet treats, 
                    including shaved ice desserts, milk tea, and other unique creations like 
                    coconut mango HoFan. They also offer popular items like Brown Sugar Ice Jelly, 
                    Mango Ice Jelly, and Coconut Mashed Taro Ice Jelly. The shop is praised for its 
                    friendly staff and fresh, handmade ingredients, like the taro balls in their 
                    ice jelly. 
                """.trimIndent(),
                phoneNumber = "+6010-882 4162",
                categories = listOf("Dessert"),
                price = "RM10-rm20",
                mealType = "Lunch",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = " Lot 10663 (Ground Floor) Pujut Commercial Centre Jalan Pujut 7,Sungai " +
                        "Merapa, Lutong, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/1gDXEHVpdF9XEyrL8",
                hours =mapOf(
                    "Monday" to "11 am - 9 pm",
                    "Tuesday" to "Closed",
                    "Wednesday" to "11 am - 9 pm",
                    "Thursday" to "11 am - 9 pm",
                    "Friday" to "11 am - 9 pm",
                    "Saturday" to "11 am - 9 pm",
                    "Sunday" to "11 am - 9 pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "QQ Dessert",
                description = """
                    QQ Dessert in Miri is a dessert shop known for its wide variety of sweet treats, 
                    including shaved ice desserts, milk tea, and other unique creations like 
                    coconut mango HoFan. They also offer popular items like Brown Sugar Ice Jelly, 
                    Mango Ice Jelly, and Coconut Mashed Taro Ice Jelly. The shop is praised for its 
                    friendly staff and fresh, handmade ingredients, like the taro balls in their 
                    ice jelly. 
                """.trimIndent(),
                phoneNumber = "+6010-882 4162",
                categories = listOf("Dessert"),
                price = "RM10-RM20",
                mealType = "Lunch",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = " Lot 10663 (Ground Floor) Pujut Commercial Centre Jalan Pujut 7,Sungai " +
                        "Merapa, Lutong, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/1gDXEHVpdF9XEyrL8",
                hours =mapOf(
                    "Monday" to "11 am - 9 pm",
                    "Tuesday" to "Closed",
                    "Wednesday" to "11 am - 9 pm",
                    "Thursday" to "11 am - 9 pm",
                    "Friday" to "11 am - 9 pm",
                    "Saturday" to "11 am - 9 pm",
                    "Sunday" to "11 am - 9 pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "Sam's Ice Cream",
                description = """
                    Sam's Ice Cream is a popular ice cream shop in Kuching, Sarawak, known for 
                    its delicious, freshly made ice cream with a variety of flavors, including 
                    unique options like Ocean (sea salt) and Gula Apong vanilla. They emphasize 
                    fresh, natural ingredients and offer both classic flavors and seasonal specials.
                """.trimIndent(),
                phoneNumber = "+6011-56319386",
                categories = listOf("Dessert"),
                price = "RM10-RM20",
                mealType = "Lunch",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "Marina Phase 2, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/5agSPrubEqFeVCkX9",
                hours =mapOf(
                    "Monday" to "9 am - 9 pm",
                    "Tuesday" to "9 am - 9 pm",
                    "Wednesday" to "9 am - 9 pm",
                    "Thursday" to "9 am - 9 pm",
                    "Friday" to "9 am - 9 pm",
                    "Saturday" to "9 am - 9 pm",
                    "Sunday" to "9 am - 9 pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "WangZai Dim Sum",
                description = """
                    Wang Zai Dim Sum in Miri is a popular spot for locals to enjoy breakfast, 
                    offering a variety of dim sum and local noodles. While some reviewers found the 
                    dim sum to be average, what sets this place apart is the exceptional service 
                    provided by friendly staff. The cleanliness of the restaurant, polite servers, 
                    and perfectly done dim sums make it a highly recommended traditional dim sum 
                    cafe in Miri.
                """.trimIndent(),
                phoneNumber = "+6011-1668 0202",
                categories = listOf("Asian", "Local"),
                price = "RM1-RM20",
                mealType = "Breakfast",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "Marina Phase 2, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/sHXokGbcaH65gTzE6",
                hours =mapOf(
                    "Monday" to "6 am - 2 pm",
                    "Tuesday" to "6 am - 2 pm",
                    "Wednesday" to "6 am - 2 pm",
                    "Thursday" to "6 am - 2 pm",
                    "Friday" to "6 am - 2 pm",
                    "Saturday" to "6 am - 2 pm",
                    "Sunday" to "6 am - 2 pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "aBui Dim Sum",
                description = """
                    aBui Dim Sum, located in Marina Square, Miri, is a new dim sum spot known for 
                    its fresh, steamed-to-order dishes and simple, cozy atmosphere. Popular items 
                    include their signature siew mai with wild prawns and the unique cakoi cheong 
                    fun. They also offer other dim sum options, mee kolok, and a custard lava bun. 
                """.trimIndent(),
                phoneNumber = "+6013-565 5889",
                categories = listOf("Asian", "Local"),
                price = "RM1-RM20",
                mealType = "Breakfast",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "Lot 2049 Ground Floor, Marina Square, Phase 1, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/aG4SoyXgGDKzXn5j6",
                hours =mapOf(
                    "Monday" to "6 am - 3 pm",
                    "Tuesday" to "6 am - 3 pm",
                    "Wednesday" to "6 am - 3 pm",
                    "Thursday" to "6 am - 3 pm",
                    "Friday" to "6 am - 3 pm",
                    "Saturday" to "6 am - 3 pm",
                    "Sunday" to "6 am - 3 pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "Nagaliar Nasi Lemak",
                description = """
                    Nagaliar Nasi Lemak in Miri is a popular eatery known for its classic Malaysian 
                    nasi lemak dish. It's a beloved spot, especially for its flavorful fried 
                    chicken and spicy sambal. The restaurant is known for its casual, welcoming 
                    atmosphere and is a great place to enjoy a traditional Malaysian breakfast or 
                    lunch. 
                """.trimIndent(),
                phoneNumber = "+6085-421 943",
                categories = listOf("Asian", "Local", "Halal"),
                price = "RM1-RM20",
                mealType = "Breakfast",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "Lot 863, Jalan Helenium, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/k9KJyCpf8CRHogAb9",
                hours =mapOf(
                    "Monday" to "715 am - 330 pm",
                    "Tuesday" to "715 am - 330 pm",
                    "Wednesday" to "715 am - 330 pm",
                    "Thursday" to "715 am - 330 pm",
                    "Friday" to "715 am - 330 pm",
                    "Saturday" to "715 am - 330 pm",
                    "Sunday" to "Closed"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "Chang Man Cafe",
                description = """
                    Chang Man Cafe is a local favorite in Miri, Sarawak, known for its affordable 
                    and satisfying Chinese-style breakfast and lunch options. Popular menu items 
                    include their signature Kolok Mee, Wantan Mee, Chicken Rice, and flavorful 
                    herbal soups. The cafe provides a simple and clean setting, ideal for a quick 
                    meal or takeaway. It's well-loved by both locals and regular working crowds for 
                    its consistent quality and quick service.
                """.trimIndent(),
                phoneNumber = "+6016-850 8889",
                categories = listOf("Asian", "Local", "Chinese"),
                price = "RM1-RM20",
                mealType = "Breakfast",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "Centerpoint, Jalan Kubu, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/cP96f1MLPNxVtfSs5",
                hours =mapOf(
                    "Monday" to "6 am - 130 pm",
                    "Tuesday" to "6 am - 130 pm",
                    "Wednesday" to "6 am - 130 pm",
                    "Thursday" to "6 am - 130 pm",
                    "Friday" to "6 am - 130 pm",
                    "Saturday" to "6 am - 130 pm",
                    "Sunday" to "6 am - 130 pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "Welcome Restaurant (Ying Xuan)",
                description = """
                    Welcome Restaurant (Ying Xuan) in Miri, Sarawak, is a popular dining spot, 
                    particularly known for its  Chinese cuisine. The restaurant is well-regarded 
                    for its breakfast, including items like nasi lemak, laksa, and dim sum, as well 
                    as its unique Polo buns. They are also known for their Chinese-style curry rice, 
                    according to Miri City Sharing.
                """.trimIndent(),
                phoneNumber = "+6014-331 6691",
                categories = listOf("Asian", "Local", "Halal"),
                price = "RM1-RM20",
                mealType = "Breakfast",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "12, Jalan Bendahara, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/c7rPbHqbRbe5JSZ86",
                hours =mapOf(
                    "Monday" to "7 am - 430 pm",
                    "Tuesday" to "7 am - 430 pm",
                    "Wednesday" to "7 am - 430 pm",
                    "Thursday" to "7 am - 430 pm",
                    "Friday" to "7 am - 430 pm",
                    "Saturday" to "7 am - 430 pm",
                    "Sunday" to "7 am - 430 pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "JJ by Jen Fusion Restaurant",
                description = """
                    JJ by Jen Fusion Restaurant in Miri, Sarawak, is a popular spot known for its 
                    delicious and affordable fusion cuisine, offering both Eastern and Western 
                    dishes. The restaurant is praised for its generous portions, making it ideal 
                    for sharing or satisfying hearty appetites. With a welcoming atmosphere and 
                    friendly service, JJ by Jen is a great place for locals and tourists alike to 
                    enjoy a satisfying meal at reasonable prices.
                """.trimIndent(),
                phoneNumber = "+6012-859 1019",
                categories = listOf("Fusion", "Brunch", "Dessert", "Asian", "Halal"),
                price = "RM10-RM20",
                mealType = "Brunch",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "Lot 1984 & 1985, Ground Floor, Marina Phase 2, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/r4JuBQ9fegfp6KBZA",
                hours =mapOf(
                    "Monday" to "10 am - 9 pm",
                    "Tuesday" to "10 am - 9 pm",
                    "Wednesday" to "10 am - 9 pm",
                    "Thursday" to "Closed",
                    "Friday" to "10 am - 9 pm",
                    "Saturday" to "10 am - 9 pm",
                    "Sunday" to "10 am - 9 pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "Hilltop Little Kitchen",
                description = """
                    Hilltop Little Kitchen is a popular Chinese restaurant in Miri, known for its 
                    decent food at affordable prices. The place offers a good view from the hill 
                    overlooking the city, but it can get crowded during peak hours, especially on 
                    weekends. Reviewers recommend making reservations to secure seats and trying 
                    dishes like Cangkok Manis Telur, XO fried rice, Mongolian pork, and more.  
                    Fresh coconuts and a serene vibe add to the dining experience.
                """.trimIndent(),
                phoneNumber = "+6017-878 7505",
                categories = listOf("Chinese","Asian"),
                price = "RM10-RM30",
                mealType = "Dinner",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "Lot 505, Q290, 98000 Miri",
                mapsUrl = "https://maps.app.goo.gl/KoWdKLaWo6QtqHVbA",
                hours =mapOf(
                    "Monday" to "630 am - 10 pm",
                    "Tuesday" to "630 am - 10 pm",
                    "Wednesday" to "630 am - 10 pm",
                    "Thursday" to "630 am - 10 pm",
                    "Friday" to "630 am - 10 pm",
                    "Saturday" to "630 am - 10 pm",
                    "Sunday" to "630 am - 10 pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "5050 Cafe Fried Kueh Tiaw ",
                description = """
                    One of Miri’s top char kueh teow stall that is only open at night, but it’s 
                    absolutely wonderful with nostalgia taste. A lot of people frequently visit 
                    this char kueh tiaw stall for dinner in Miri. They had been running the stall 
                    for over 20 years.
                """.trimIndent(),
                phoneNumber = "N/A",
                categories = listOf("Chinese", "Asian"),
                price = "RM10-RM30",
                mealType = "Dinner",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "5050 Cafe Miri\n" +
                        "604, Jalan Pelita 3, Pelita Commercial Centre, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/dxK1CkmYPzF7ZhAv5",
                hours =mapOf(
                    "Monday" to "5 pm - 9pm",
                    "Tuesday" to "5 pm - 9pm",
                    "Wednesday" to "5 pm - 9pm",
                    "Thursday" to "5 pm - 9pm",
                    "Friday" to "5 pm - 9pm",
                    "Saturday" to "5 pm - 9pm",
                    "Sunday" to "5 pm - 9pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "Saigon Republic Vietnamese Cuisine",
                description = """
                    Saigon Republic Vietnamese Cuisine is a well-loved Vietnamese restaurant in 
                    Miri offering a wide range of authentic dishes such as 
                    Pho (Vietnamese Beef Noodles), Banh Mi, Spring Rolls, and Vietnamese Coffee. 
                    The atmosphere is simple yet inviting, with a focus on fresh herbs, savory 
                    broths, and traditional Vietnamese flavors. It is a go-to spot for those 
                    seeking healthy, hearty, and aromatic meals. The staff are known for their warm 
                    hospitality and quick service.
                """.trimIndent(),
                phoneNumber = "+6010-775 2552",
                categories = listOf("Vietnamese", "Asian"),
                price = "RM10-RM35",
                mealType = "Dinner",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "Jalan Merbau, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/UYjAeeZtCJNLeTSv5",
                hours =mapOf(
                    "Monday" to "1030 am - 9 pm",
                    "Tuesday" to "1030 am - 9 pm",
                    "Wednesday" to "1030 am - 9 pm",
                    "Thursday" to "1030 am - 9 pm",
                    "Friday" to "1030 am - 9 pm",
                    "Saturday" to "1030 am - 9 pm",
                    "Sunday" to "1030 am - 9 pm",
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "Kenny's",
                description = """
                    Kenny's in Miri is known for its fusion cuisine, blending Western and Japanese 
                    dishes. It's a popular spot, especially for those seeking creative and 
                    well-crafted meals, with dishes like ahi katsu, mentaiko pasta, and a unique 
                    abalone broth. The restaurant, also known as Hachi by Kenny's, is praised for 
                    its innovative menu and ambience, often recommended for special occasions.
                """.trimIndent(),
                phoneNumber = "+6014-325 7897",
                categories = listOf("Western", "Fusion"),
                price = "RM30-Rm50",
                mealType = "Dinner",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "Lot 2133, Off Krokop, 10, Jalan Datuk Edward Jeli, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/C8CNRf55EZ5eA4Es7",
                hours =mapOf(
                    "Monday" to "Closed",
                    "Tuesday" to "5 pm - 10 pm",
                    "Wednesday" to "5 pm - 10 pm",
                    "Thursday" to "5 pm - 10 pm",
                    "Friday" to "5 pm - 10 pm",
                    "Saturday" to "5 pm - 10 pm",
                    "Sunday" to "5 pm - 10 pm",
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "Zaika Restaurant (Zaika உணவகம்/ज़ाइक़ा रेस्टोरेंट)",
                description = """
                    Zaika, located in Miri, is a highly-regarded Northern Indian restaurant known 
                    for its delicious and authentic cuisine, generous portions, and cozy atmosphere. 
                    Popular dishes include palak paneer, potato naan, and various curries like 
                    butter chicken and lamb biryani. While the ambiance is pleasant, some find the 
                    interior a bit dated. The restaurant is praised for its excellent service and 
                    friendly staff. 
                """.trimIndent(),
                phoneNumber = "+6085-410 155",
                categories = listOf("Indian", "Halal", "Vegetarian"),
                price = "RM20-Rm40",
                mealType = "Dinner",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = " Lot 2512, Jalan Miri-Pujut, Boulevard Commercial Centre, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/zUfU7qhDb82B6qzG8",
                hours =mapOf(
                    "Monday" to "11 am - 10 pm",
                    "Tuesday" to "11 am - 10 pm",
                    "Wednesday" to "11 am - 10 pm",
                    "Thursday" to "11 am - 10 pm",
                    "Friday" to "11 am - 10 pm",
                    "Saturday" to "11 am - 10 pm",
                    "Sunday" to "11 am - 10 pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "Awind’s Nasi Kukus",
                description = """
                    Awind Nasi Kukus in Miri, Sarawak, is a popular spot known for its delicious 
                    and aromatic Nasi Kukus. It's a must-try for those seeking a flavorful Malaysian 
                    dish with fragrant steamed rice, tender chicken, and various flavorful sauces. 
                    They also have a great ambience and are open late, making it a great place for a 
                    meal. Awind Nasi Kukus is a halal restaurant that offers a variety of Nasi 
                    Kukus options, including chicken, fish, and lamb dishes. Some popular choices 
                    include Nasi Kukus Ayam Krispy, Nasi Kukus Ikan Tempura Krispy, and Nasi Kukus 
                    Ayam Bakar. They also offer a premium version and options with eggs. 
                """.trimIndent(),
                phoneNumber = "+6012-889 5398",
                categories = listOf("Malaysian", "Halal", "Local"),
                price = "RM10-Rm15",
                mealType = "Lunch",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "Bangunan Persatuan Jatti Meirek Miri, Lot 1749, Jln Jee Foh Utama, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/9h7YBQ85GqKzDqYa7",
                hours =mapOf(
                    "Monday" to "11 am - 11 pm",
                    "Tuesday" to "11 am - 11 pm",
                    "Wednesday" to "11 am - 11 pm",
                    "Thursday" to "11 am - 11 pm",
                    "Friday" to "11 am - 11 pm",
                    "Saturday" to "11 am - 11 pm",
                    "Sunday" to "11 am - 11 pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            ),

            Restaurant(
                title = "The Nest Grill & Lounge",
                description = """
                    The Nest Grill & Lounge is an upscale restaurant and bar located in the heart 
                    of Miri, offering a refined dining experience with a fusion of Western and 
                    Asian cuisines. It is known for its grilled meats, seafood platters, pastas, 
                    and stylish cocktails. With modern décor, ambient lighting, and live music on 
                    select nights, The Nest provides a cozy yet elegant atmosphere ideal for dinner 
                    dates, family gatherings, or casual hangouts.
                """.trimIndent(),
                phoneNumber = "+6016-963 5796",
                categories = listOf("Western", "Asian Fusion", "Grill"),
                price = "RM40-Rm80",
                mealType = "Dinner",
                imageGallery = listOf(R.drawable.joa_1, R.drawable.joa_2), // insert pictures later
                address = "No A-G18, Miri Times Square, Marina Parkcity, 98000, 98000 Miri, Sarawak",
                mapsUrl = "https://maps.app.goo.gl/vWFjcjvhfEwwe9Mf8",
                hours =mapOf(
                    "Monday" to "9 am - 10 pm",
                    "Tuesday" to "9 am - 10 pm",
                    "Wednesday" to "9 am - 10 pm",
                    "Thursday" to "9 am - 10 pm",
                    "Friday" to "9 am - 11 pm",
                    "Saturday" to "9 am - 11 pm",
                    "Sunday" to "9 am - 10 pm"
                ),
                rating = 4.0f,        // Added this line
                priceLevel= 2
            )
        )


    }
}