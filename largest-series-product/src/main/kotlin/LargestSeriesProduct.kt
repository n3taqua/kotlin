import javax.xml.stream.events.Characters

class Series {
   private var input:String=""
    constructor(input: String){
        for (item in input)
            if(item.toLong()<48||item.toLong()>57) throw IllegalArgumentException();
        this.input=input;
    }

    fun getLargestProduct(span: Int): Long {
        if (input.length<span)
            throw IllegalArgumentException();
        if (span==-1)
            throw IllegalArgumentException();
        if (span==0 || span==null ) return 1;
        var preSolve=input;
        var max=0L;
        for (i in 1..span) preSolve += '0'
        for (i1 in input.indices){
            var temp=1L;
            for(i2 in 0 until span){
                temp*=Character.getNumericValue(preSolve[i1+i2]).toLong();
            }
            if(max<temp)
                max=temp;
        }

        return max;
    }
}
