package knockoff

import org.scalatest._
import org.scalatest.matchers._

class ChunkParsersSpec extends ChunkParser with Spec with ShouldMatchers {
    
    describe("ChunkParser") {
      it("should handle simple bullet items") {
        val src = "* item 1\n* item 2\n"
        parse( chunk, src ).get should equal ( BulletLineChunk("item 1\n") )
      }

      it("should group a second line that's not a bullet") {
        val src = "*   item 1\n    more\n"
        parse( chunk, src ).get should equal (
          BulletLineChunk("item 1\nmore\n")
        )
      }
      
      it("should ignore whitespace around headers") {
        val src = "# Header 1 #"
        parse( chunk, src ).get should equal { HeaderChunk(1, "Header 1") }
      }
    }
}
