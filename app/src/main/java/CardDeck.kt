class CardDeck() {

    // ======== State ========
    private val cardList = mutableListOf<Card>()
    lateinit var currentCard : Card

    // ======== Init ========
    init {
        initilizeCards()
        getNewCard()
    }

    // ======== Card setup ========
    fun initilizeCards() {
        cardList.add(Card("heart"))
        cardList.add(Card("spade"))
        cardList.add(Card("club"))
        cardList.add(Card("diamond"))
    }

    // ======== Draw new card function ========
    fun getNewCard() : Card {
        val rnd = (0 until cardList.size).random()
        currentCard = cardList[rnd]
        return currentCard
    }
}