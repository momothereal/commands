/*
 * Copyright (c) 2016-2017 Daniel Ennis (Aikar) - MIT License
 *
 *  Permission is hereby granted, free of charge, to any person obtaining
 *  a copy of this software and associated documentation files (the
 *  "Software"), to deal in the Software without restriction, including
 *  without limitation the rights to use, copy, modify, merge, publish,
 *  distribute, sublicense, and/or sell copies of the Software, and to
 *  permit persons to whom the Software is furnished to do so, subject to
 *  the following conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 *  LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 *  OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 *  WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package co.aikar.commands;

import co.aikar.commands.apachecommonslang.ApacheCommonsLangUtil;

import java.util.List;

public class ForwardingCommand extends BaseCommand {
    private final BaseCommand command;
    private final String[] baseArgs;

    ForwardingCommand(BaseCommand command, String[] baseArgs) {
        this.commandName = command.commandName;
        this.command = command;
        this.baseArgs = baseArgs;
    }

    @Override
    public boolean hasPermission(CommandIssuer sender) {
        return command.hasPermission(sender);
    }

    @Override
    public List<String> tabComplete(CommandIssuer issuer, String alias, String[] args) throws IllegalArgumentException {
        return command.tabComplete(issuer, alias, ApacheCommonsLangUtil.addAll(baseArgs, args));
    }

    @Override
    public void execute(CommandIssuer issuer, String commandLabel, String[] args) {
        command.execute(issuer, commandLabel, ApacheCommonsLangUtil.addAll(baseArgs, args));
    }
}
