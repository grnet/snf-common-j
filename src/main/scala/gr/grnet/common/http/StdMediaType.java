/*
 * Copyright (C) 2010-2014 GRNET S.A.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package gr.grnet.common.http;

/**
 * Provides a few standard <code>HTTP</code> media types.
 */
public enum StdMediaType implements IMediaType {
    Application_Directory("application/directory"),
    Application_Folder("application/folder"),
    Text_Plain("text/plain"),
    Text_Html("text/html"),
    Application_Json("application/json");

    private final String value;

    StdMediaType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public boolean is(String contentType) {
        return this.value.equals(contentType);
    }
}
