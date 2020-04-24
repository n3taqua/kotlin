import kotlin.concurrent.thread

object Hamming {

    fun compute(leftStrand: String, rightStrand: String): Int {
       if(leftStrand.length!=rightStrand.length){
           throw  IllegalArgumentException("left and right strands must be of equal length")
       }
        var count=0;
        leftStrand.forEachIndexed { index, c ->if (c!=rightStrand[index]) count++  };
        return count;
    }
}
