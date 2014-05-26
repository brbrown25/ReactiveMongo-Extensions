// Copyright (C) 2014 the original author or authors.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package reactivemongo.extensions.dao

import reactivemongo.extensions.bson.model.Event
import reactivemongo.api.DefaultDB
import reactivemongo.api.indexes.{ Index, IndexType }
import reactivemongo.bson.BSONObjectID
import scala.concurrent.{ Future, Await }
import scala.concurrent.duration._

class EventBsonDao(_db: DefaultDB) extends BsonDao[Event, String](() => _db, "events") {

  def findByTitle(title: String): Future[Option[Event]] = {
    findOne("title" $eq title)
  }

  def dropDatabaseSync(): Boolean = {
    Await.result(_db.drop(), 20 seconds)
  }
}