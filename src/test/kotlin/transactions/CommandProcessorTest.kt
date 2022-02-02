package transactions

import com.example.main.CommandProcessor
import com.example.main.In
import com.example.main.Out
import com.example.main.Strings.KEY_NOT_SET
import com.example.main.Strings.NO_TRANSACTION
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import org.junit.jupiter.api.Test

internal class CommandProcessorTest {

    private val output: Out = mockk(relaxed = true)
    private val input: In = mockk()

    private val commandProcessor = CommandProcessor(input, output)

    @Test
    fun `set and get`() {
        whenInput(
            "set foo 123",
            "get foo",
            "exit",
        )

        commandProcessor.start()

        verifyOutput(
            "123"
        )
    }

    @Test
    fun `delete and get`() {
        whenInput(
            "delete foo",
            "get foo",
            "exit"
        )

        commandProcessor.start()

        verifyOutput(
            KEY_NOT_SET
        )
    }

    @Test
    fun count() {
        whenInput(
            "set foo 123",
            "set bar 456",
            "set baz 123",
            "count 123",
            "count 456",
            "exit",
        )

        commandProcessor.start()

        verifyOutput(
            "Keys with given values = 2",
            "Keys with given values = 1"
        )
    }

    @Test
    fun `commit and rollback`() {
        whenInput(
            "begin",
            "set foo 456",
            "commit",
            "rollback",
            "get foo",
            "exit",
        )
        commandProcessor.start()

        verifyOutput(
            NO_TRANSACTION,
            "456"
        )
    }

    @Test
    fun `rollback transaction`() {
        whenInput(
            "set foo 123",
            "set bar abc",
            "begin",
            "set foo 456",
            "get foo",
            "set bar def",
            "get bar",
            "rollback",
            "get foo",
            "get bar",
            "commit",
            "exit",
        )

        commandProcessor.start()

        verifyOutput(
            "456",
            "def",
            "123",
            "abc",
            NO_TRANSACTION
        )
    }

    @Test
    fun `nested transaction`() {
        whenInput(
            "set foo 123",
            "begin",
            "set foo 456",
            "begin",
            "set foo 789",
            "get foo",
            "rollback",
            "get foo",
            "rollback",
            "get foo",
            "exit",
        )

        commandProcessor.start()

        verifyOutput(
            "789",
            "456",
            "123",
        )
    }

    private fun whenInput(vararg lines: String) {
        every { input.readLine() } returnsMany listOf(*lines)
    }

    private fun verifyOutput(vararg lines: String) {
        verifySequence {
            lines.forEach(output::print)
        }
    }
}
