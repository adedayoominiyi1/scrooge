package {{package}};

import com.twitter.finagle.SourcedException;
import com.twitter.scrooge.ThriftStruct;
import com.twitter.scrooge.ThriftStructCodec;
import com.twitter.scrooge.Utilities;
import com.twitter.util.Future;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import org.apache.thrift.protocol.*;
import org.apache.thrift.TApplicationException;
{{#imports}}
import {{parentPackage}}.{{subPackage}}.*;
{{/imports}}
{{#finagleClient}}
import com.twitter.finagle.stats.NullStatsReceiver;
import com.twitter.finagle.stats.StatsReceiver;
import com.twitter.finagle.thrift.ThriftClientRequest;
import com.twitter.scrooge.FinagleThriftClient;
{{/finagleClient}}
{{#finagleService}}
import com.twitter.finagle.stats.Counter;
import com.twitter.scrooge.FinagleThriftService;
import com.twitter.util.Function;
import com.twitter.util.Function2;
{{/finagleService}}
{{#ostrichServer}}
import com.twitter.finagle.builder.Server;
import com.twitter.finagle.builder.ServerBuilder;
import com.twitter.finagle.stats.StatsReceiver;
import com.twitter.finagle.stats.OstrichStatsReceiver;
import com.twitter.finagle.thrift.ThriftServerFramedCodec;
import com.twitter.finagle.tracing.NullTracer;
import com.twitter.finagle.tracing.Tracer;
import com.twitter.logging.Logger;
{{/ostrichServer}}

public class {{name}} {
  public interface Iface {{syncExtends}}{
{{#syncFunctions}}
    {{>function}};
{{/syncFunctions}}
  }

  public interface FutureIface {{asyncExtends}}{
{{#asyncFunctions}}
    {{>function}};
{{/asyncFunctions}}
  }

{{#structs}}
  {{>struct}}
{{/structs}}
{{#finagleClients}}
  {{>finagleClient}}
{{/finagleClients}}
{{#finagleServices}}
  {{>finagleService}}
{{/finagleServices}}
{{#ostrichServers}}
  {{>ostrichServer}}
{{/ostrichServers}}
}
