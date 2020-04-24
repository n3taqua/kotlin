object SumOfMultiples {

    fun sum(factors: Set<Int>, limit: Int): Int {
        var sum=0;
        var arr= arrayListOf<Int>();
           var temp= factors.forEach {
                var i=1;
                var loop=true;
                while(loop){
                    if(it==0)
                        loop=false;
                    if (it*i<limit)
                        arr.add(it*i);
                    else
                        loop=false;
                    i++;
                }
            }
            sum=arr.filterIndexed { index, unit -> arr.indexOf(unit)==index  }.sum();

        return  sum;
    }
}
