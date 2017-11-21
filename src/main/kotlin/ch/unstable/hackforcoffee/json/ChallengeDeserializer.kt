package ch.unstable.hackforcoffee.json

import ch.unstable.hackforcoffee.model.Challenge
import com.google.gson.JsonParseException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

import java.io.IOException
import kotlin.reflect.KClass

class ChallengeDeserializer : TypeAdapter<Challenge>() {
    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: Challenge) {
        out.beginObject()
        out.name("instructions").value(value.instructions)
        out.name("active").value(value.active)
        out.name("solved").value(value.solved)
        out.name("id").value(value.id)
        out.endObject()
    }

    @Throws(IOException::class)
    override fun read(reader: JsonReader): Challenge? {
        reader.beginObject()
        var instructions: String? = null
        var solution: String? = null
        for(i in 0..2) {
            when(reader.nextName()) {
                "instructions" -> instructions = reader.nextString()
                "solution" -> solution = reader.nextString()
            }
        }

        if(solution == null) throw MissingJsonFieldException(Challenge::class, "solution")
        if(instructions == null) throw MissingJsonFieldException(Challenge::class, "instructions")

        reader.endObject()

        return Challenge(
                instructions = instructions,
                solution = solution
        )
    }
}


class MissingJsonFieldException(kClass: KClass<out Any>, field:String):
        JsonParseException("${kClass.simpleName} must have '$field' property")